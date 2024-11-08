package com.swanhtetaungphyo.mysqladmin.utils;

import com.swanhtetaungphyo.mysqladmin.dto.QueryRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Query {



  public String queryBuilder(OPERATION_TOKEN operation, QueryRequest queryRequest, List<String> values) {
    return switch (operation) {
      case SELECT -> buildSelectQuery(queryRequest);
      case INSERT -> buildInsertQuery(queryRequest, values);
      case UPDATE -> buildUpdateQuery(queryRequest, values);
      case DELETE -> buildDeleteQuery(queryRequest);
    };
  }

  private String buildSelectQuery(QueryRequest queryRequest) {
    // Building SELECT statement
    String columns = queryRequest.getColumns() != null && !queryRequest.getColumns().isEmpty() ?
        String.join(", ", queryRequest.getColumns()) : "*";
    String query = "SELECT " + columns + " FROM " + queryRequest.getTableName();

    // Handle conditions if any
    query += buildWhereClause(queryRequest.getConditions());

    return query;
  }

  private String buildInsertQuery(QueryRequest queryRequest, List<String> values) {
    // Ensure the columns and values have the same size
    if (queryRequest.getColumns().size() != values.size()) {
      throw new IllegalArgumentException("Columns and values size must match");
    }

    // Building the INSERT statement
    String columns = String.join(", ", queryRequest.getColumns());
    String valuesStr = values.stream().map(v -> "'" + v + "'").collect(Collectors.joining(", "));

    return "INSERT INTO " + queryRequest.getTableName() +
        " (" + columns + ") VALUES (" + valuesStr + ")";
  }

  private String buildUpdateQuery(QueryRequest queryRequest, List<String> values) {

    if (queryRequest.getColumns().size() != values.size()) {
      throw new IllegalArgumentException("Columns and values size must match");
    }


    String setClause = IntStream.range(0, queryRequest.getColumns().size())
        .mapToObj(i -> queryRequest.getColumns().get(i) + " = '" + values.get(i) + "'")
        .collect(Collectors.joining(", "));

    String query = "UPDATE " + queryRequest.getTableName() + " SET " + setClause;
    query += buildWhereClause(queryRequest.getConditions());

    return query;
  }

  private String buildDeleteQuery(QueryRequest queryRequest) {

    return "DELETE FROM " + queryRequest.getTableName() +
        buildWhereClause(queryRequest.getConditions());
  }

  private String buildWhereClause(Map<String, String> conditions) {
    if (conditions != null && !conditions.isEmpty()) {
      return " WHERE " + conditions.entrySet()
          .stream()
          .map(entry -> entry.getKey() + " = '" + entry.getValue() + "'")
          .collect(Collectors.joining(" AND "));
    }
    return "";
  }
}

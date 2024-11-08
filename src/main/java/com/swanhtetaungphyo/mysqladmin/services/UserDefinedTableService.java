package com.swanhtetaungphyo.mysqladmin.services;

import com.swanhtetaungphyo.mysqladmin.dto.QueryRequest;
import com.swanhtetaungphyo.mysqladmin.models.UserDefinedColumn;
import com.swanhtetaungphyo.mysqladmin.models.UserDefinedTable;
import com.swanhtetaungphyo.mysqladmin.repo.UserDefinedTableRepository;
import com.swanhtetaungphyo.mysqladmin.utils.OPERATION_TOKEN;
import com.swanhtetaungphyo.mysqladmin.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDefinedTableService {

  private final JdbcTemplate jdbcTemplate;
  private final UserDefinedTableRepository userDefinedTableRepository;

  public UserDefinedTableService(JdbcTemplate jdbcTemplate, UserDefinedTableRepository userDefinedTableRepository) {
    this.jdbcTemplate = jdbcTemplate;
    this.userDefinedTableRepository = userDefinedTableRepository;
  }

  @Transactional
  public void createTable(UserDefinedTable userDefinedTable) {
    StringBuilder queryBuilder = new StringBuilder()
        .append("CREATE TABLE ")
        .append(userDefinedTable.getTableName())
        .append(" (id BIGINT PRIMARY KEY AUTO_INCREMENT");

    for (UserDefinedColumn column : userDefinedTable.getColumns()) {
      queryBuilder.append(", ")
          .append(column.getColumnName())
          .append(" ")
          .append(column.getColumnType());
    }

    queryBuilder.append(");");

    String queryCommand = queryBuilder.toString();
    jdbcTemplate.execute(queryCommand);

    userDefinedTableRepository.save(userDefinedTable);
  }

  @Transactional
  public List<Map<String, Object>> readTable(QueryRequest queryRequest) {
    Query queryBuilder = new Query();
    String queryString = queryBuilder.queryBuilder(
        OPERATION_TOKEN.valueOf(queryRequest.getOperation().toUpperCase()), queryRequest, null);

    return jdbcTemplate.queryForList(queryString);
  }

  @Transactional
  public void insertData(QueryRequest queryRequest) {

    if (queryRequest.getColumns() == null || queryRequest.getColumns().isEmpty()) {
      throw new IllegalArgumentException("Columns cannot be empty.");
    }


    Query queryUtil = new Query();
    List<String> values = queryRequest.getColumns();
    String insertQuery = queryUtil.queryBuilder(OPERATION_TOKEN.INSERT, queryRequest, values);


    jdbcTemplate.update(insertQuery);
  }
}

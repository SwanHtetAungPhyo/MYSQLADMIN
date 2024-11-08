package com.swanhtetaungphyo.mysqladmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class QueryRequest {
  @JsonProperty(namespace = "table_name")
  private String tableName;
  @JsonProperty(namespace = "operation")
  private String operation;
  @JsonProperty(namespace = "columns")
  private List<String> columns;
  @JsonProperty(namespace = "conditions")
  private Map<String, String> conditions;

  @JsonProperty(namespace = "values")
  private List<String> values;
}

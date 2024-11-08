package com.swanhtetaungphyo.mysqladmin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_defined_column")
public class UserDefinedColumn {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_defined_table_id")
  private UserDefinedTable userDefinedTable;

  private String columnName;
  private String columnType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserDefinedTable getUserDefinedTable() {
    return userDefinedTable;
  }

  public void setUserDefinedTable(UserDefinedTable userDefinedTable) {
    this.userDefinedTable = userDefinedTable;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getColumnType() {
    return columnType;
  }

  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }
}
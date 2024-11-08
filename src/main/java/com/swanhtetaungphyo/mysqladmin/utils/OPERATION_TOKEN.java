package com.swanhtetaungphyo.mysqladmin.utils;

import lombok.Getter;

@Getter
public enum OPERATION_TOKEN {
  SELECT("select"),
  INSERT("insert"),
  UPDATE("update"),
  DELETE("delete");

  private final String operation;

  OPERATION_TOKEN(String operation) {
    this.operation = operation;
  }

}
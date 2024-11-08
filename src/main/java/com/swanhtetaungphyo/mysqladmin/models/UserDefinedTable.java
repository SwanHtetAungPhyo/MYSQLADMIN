package com.swanhtetaungphyo.mysqladmin.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class UserDefinedTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tableName;

  @Getter
  @jakarta.persistence.OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "userDefinedTable")
  private List<UserDefinedColumn> columns;


}

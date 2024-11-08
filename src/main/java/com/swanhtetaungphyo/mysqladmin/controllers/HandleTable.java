package com.swanhtetaungphyo.mysqladmin.controllers;

import com.swanhtetaungphyo.mysqladmin.dto.QueryRequest;
import com.swanhtetaungphyo.mysqladmin.models.UserDefinedTable;
import com.swanhtetaungphyo.mysqladmin.services.UserDefinedTableService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tables")
public class HandleTable {

  private final UserDefinedTableService userDefinedTableService;

  public HandleTable(UserDefinedTableService userDefinedTableService) {
    this.userDefinedTableService = userDefinedTableService;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody UserDefinedTable userDefinedTable) {
    userDefinedTableService.createTable(userDefinedTable);
    return ResponseEntity.ok().body("Created");
  }

  @GetMapping
  public ResponseEntity<?> read(@RequestBody QueryRequest tableReader) {
    List<Map<String, Object>> result = userDefinedTableService.readTable(tableReader);
    return ResponseEntity.ok().body(result);
  }
  @PostMapping("/insert")
  public ResponseEntity<?> insert(@RequestBody QueryRequest queryRequest) {
    try {
      userDefinedTableService.insertData(queryRequest);
      return ResponseEntity.ok().body("Data inserted successfully.");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error inserting data: " + e.getMessage());
    }
  }

  @DeleteMapping
  public ResponseEntity<?> delete(@RequestParam String tableName) {
    return ResponseEntity.ok().body("Success");
  }
}

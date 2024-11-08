package com.swanhtetaungphyo.mysqladmin.repo;

import com.swanhtetaungphyo.mysqladmin.models.UserDefinedTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDefinedTableRepository extends JpaRepository<UserDefinedTable, Long> {

}
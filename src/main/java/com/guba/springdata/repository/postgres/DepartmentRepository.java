package com.guba.springdata.repository.postgres;

import com.guba.springdata.domain.postgres.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

package com.guba.springdata.repository.mysql;

import com.guba.springdata.domain.mysql.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

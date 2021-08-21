package com.guba.springdata.repository.mariadb;

import com.guba.springdata.domain.mariadb.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

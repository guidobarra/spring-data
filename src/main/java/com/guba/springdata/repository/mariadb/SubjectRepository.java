package com.guba.springdata.repository.mariadb;

import com.guba.springdata.domain.mariadb.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

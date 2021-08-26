package com.guba.springdata.repository.postgres;

import com.guba.springdata.domain.postgres.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}

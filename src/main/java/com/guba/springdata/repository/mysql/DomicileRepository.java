package com.guba.springdata.repository.mysql;

import com.guba.springdata.domain.mysql.Domicile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicileRepository extends JpaRepository<Domicile, Long> {
}

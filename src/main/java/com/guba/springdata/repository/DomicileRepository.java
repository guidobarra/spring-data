package com.guba.springdata.repository;

import com.guba.springdata.domain.Domicile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicileRepository extends JpaRepository<Domicile, Long> {
}

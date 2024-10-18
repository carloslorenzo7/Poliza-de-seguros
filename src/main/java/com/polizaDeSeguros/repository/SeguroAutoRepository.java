package com.polizaDeSeguros.repository;

import com.polizaDeSeguros.model.entity.SeguroAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroAutoRepository extends JpaRepository<SeguroAuto, Long> {
}


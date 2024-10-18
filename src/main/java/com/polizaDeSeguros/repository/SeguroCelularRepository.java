package com.polizaDeSeguros.repository;

import com.polizaDeSeguros.model.entity.SeguroCelular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroCelularRepository extends JpaRepository<SeguroCelular, Long> {
}

package com.polizaDeSeguros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polizaDeSeguros.model.entity.Poliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {

}

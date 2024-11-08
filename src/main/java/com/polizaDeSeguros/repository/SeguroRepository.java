package com.polizaDeSeguros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polizaDeSeguros.model.entity.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
    
}
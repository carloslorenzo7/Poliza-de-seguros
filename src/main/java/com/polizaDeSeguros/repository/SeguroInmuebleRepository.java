package com.polizaDeSeguros.repository;

import com.polizaDeSeguros.model.entity.SeguroInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroInmuebleRepository extends JpaRepository<SeguroInmueble, Long> {
}

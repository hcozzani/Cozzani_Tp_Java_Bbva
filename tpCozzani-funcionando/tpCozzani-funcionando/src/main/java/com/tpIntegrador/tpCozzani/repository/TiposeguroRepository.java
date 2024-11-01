package com.tpIntegrador.tpCozzani.repository;

import com.tpIntegrador.tpCozzani.modelo.TipoSeguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposeguroRepository extends JpaRepository<TipoSeguro,Long> {
}

package com.tpIntegrador.tpCozzani.repository;

import com.tpIntegrador.tpCozzani.modelo.Cliente;
import com.tpIntegrador.tpCozzani.modelo.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {

}

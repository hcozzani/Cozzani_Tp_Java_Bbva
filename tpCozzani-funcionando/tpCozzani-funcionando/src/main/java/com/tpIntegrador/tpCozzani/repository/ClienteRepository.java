package com.tpIntegrador.tpCozzani.repository;

import com.tpIntegrador.tpCozzani.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByDni(String dni);

}

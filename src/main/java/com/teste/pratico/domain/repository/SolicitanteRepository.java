package com.teste.pratico.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.model.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>{

}
package com.teste.pratico.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query(value = "select * from agendamento a join solicitante s on s.id = a.solicitante_id where a.data between :dataInicio and :dataFinal and a.solicitante_id = :codigoSolicitante", nativeQuery = true)
	List<Agendamento> findAgendamentosPorDataESolicitante(LocalDate dataInicio, LocalDate dataFinal, Long codigoSolicitante);
	
	@Query(value = "select * from agendamento a join solicitante s on s.id = a.solicitante_id where a.data between :dataInicio and :dataFinal", nativeQuery = true)
	List<Agendamento> findAgendamentosPorDataInicialEDataFinal(LocalDate dataInicio, LocalDate dataFinal);

}
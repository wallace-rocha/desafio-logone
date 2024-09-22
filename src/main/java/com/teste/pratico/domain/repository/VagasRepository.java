package com.teste.pratico.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.pratico.domain.model.Vagas;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long>{
	
	@Query(value = "select coalesce(sum(quantidade), 0) from vagas where :data between data_inicial and data_final", nativeQuery = true)
	Integer findTotalVagasPorDataAgendamento(LocalDate data);
	
	@Query(value = "select coalesce(sum(quantidade), 0) from vagas where data_inicial <= :dataFinal or data_final >= :dataInicial", nativeQuery = true)
	Integer findTotalVagasPorDataInicialEDataFinal(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select * from vagas where data_inicial <= :dataFinal or data_final >= :dataInicial", nativeQuery = true)
	List<Vagas> findPorDataInicialEDataFinal(LocalDate dataInicial, LocalDate dataFinal);

}
package com.teste.pratico.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.model.Vagas;
import com.teste.pratico.domain.repository.VagasRepository;

@Service
public class VagasService {

	@Autowired
	private VagasRepository vagasRepository;

	public void inserirVaga(Vagas vagas) throws Exception {
		LocalDate dataAtual = LocalDate.now();
		if (vagas.getDataInicial().isBefore(dataAtual)) {
			throw new Exception("Data Inválida: A data inicial deve ser igual ou posterior à data atual.");
		} else if (vagas.getQuantidade() < 1) {
			throw new Exception("Quantidade Inválida: A quantidade deve ser maior que zero.");
		}
		vagasRepository.save(vagas);
	}
	
	public int findTotalVagasPorDataAgendamento(LocalDate data) throws Exception {
		int quantidadeTotal = vagasRepository.findTotalVagasPorDataAgendamento(data);
		return quantidadeTotal;
	}

	public int findTotalVagasPorDataInicialEDataFinal(LocalDate dataInicial, LocalDate dataFinal) {
		int quantidadeTotal = vagasRepository.findTotalVagasPorDataInicialEDataFinal(dataInicial, dataFinal);
		return quantidadeTotal;
	}
	
	public List<Vagas> findPorDataInicialEDataFinal(LocalDate dataInicial, LocalDate dataFinal) throws Exception {
		List<Vagas> vagas = vagasRepository.findPorDataInicialEDataFinal(dataInicial, dataFinal);
		if (Objects.isNull(vagas) || vagas.isEmpty()) {
			throw new Exception("Sem Vagas: Não há vagas disponíveis para a data solicitada.");
		}
		return vagas;
	}

}
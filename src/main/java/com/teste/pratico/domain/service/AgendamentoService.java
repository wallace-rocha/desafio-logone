package com.teste.pratico.domain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.model.Agendamento;
import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.domain.model.Vagas;
import com.teste.pratico.domain.model.dto.AgendamentoDTO;
import com.teste.pratico.domain.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	@Autowired
	private VagasService vagasService;
	
	public static DateTimeFormatter FORMATAR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void validarInserirAgendamento(Agendamento agendamento) throws Exception {

		List<Vagas> vagas = vagasService.findPorDataInicialEDataFinal(agendamento.getData(), agendamento.getData());
		int quantidadeVagas = vagasService.findTotalVagasPorDataAgendamento(agendamento.getData());
		
		validarDataAgendamento(agendamento);
		validarQuantidadeAgendamentosDisponiveis(agendamento, quantidadeVagas, vagas);
		validarQuantidadeAgendamentosPorSolicitante(agendamento, quantidadeVagas, vagas);
	}

	private void validarDataAgendamento(Agendamento agendamento) throws Exception {
		
		LocalDate dataAtual = LocalDate.now();
		if (agendamento.getData().isBefore(dataAtual)) {
			throw new Exception("Data Inválida: A data do agendamento deve ser igual ou posterior à data atual.");
		}
	}

	private void validarQuantidadeAgendamentosDisponiveis(Agendamento agendamento, int quantidadeVagas, List<Vagas> vagas) throws Exception {

		Map<String, LocalDate> datas = obterDataInicialEFinal(vagas);
	    LocalDate dataInicial = datas.get("dataInicial");
	    LocalDate dataFinal = datas.get("dataFinal");
		
		List<Agendamento> agendamentosTotal = agendamentoRepository.findAgendamentosPorDataInicialEDataFinal(dataInicial, dataFinal);
		if (quantidadeVagas - agendamentosTotal.size() < 1) {
			throw new Exception("Vagas Esgotadas: Não há vagas para o período de " + FORMATAR_DATA.format(dataInicial) + " a " + FORMATAR_DATA.format(dataFinal) + ".");
		}
	}

	private void validarQuantidadeAgendamentosPorSolicitante(Agendamento agendamento, int quantidadeVagas,
			List<Vagas> vagas) throws Exception {
		
		Map<String, LocalDate> datas = obterDataInicialEFinal(vagas);
	    LocalDate dataInicial = datas.get("dataInicial");
	    LocalDate dataFinal = datas.get("dataFinal");
		
		List<Agendamento> agendamentosPorSolicitante = agendamentoRepository.findAgendamentosPorDataESolicitante(dataInicial, dataFinal, agendamento.getCodigoSolicitante());
		double quantidadeAgendamentoPermitido = quantidadeVagas * 25 / 100;
		if (!agendamentosPorSolicitante.isEmpty() && quantidadeAgendamentoPermitido - agendamentosPorSolicitante.size() < 1) {
			throw new Exception("Limite Alcançado: Você atingiu o limite de agendamentos para o período de " + FORMATAR_DATA.format(dataInicial) + " a " + FORMATAR_DATA.format(dataFinal) + ".");
		}
		
	}
	
	private Map<String, LocalDate> obterDataInicialEFinal(List<Vagas> vagas) {
	    Map<String, LocalDate> datas = new HashMap<>();
	    LocalDate dataInicial = LocalDate.now();
	    LocalDate dataFinal = LocalDate.now();
	    for (Vagas vaga : vagas) {
	        if (vaga.getDataInicial().isBefore(dataInicial)) {
	            dataInicial = vaga.getDataInicial();
	        } if (vaga.getDataFinal().isAfter(dataFinal)) {
	            dataFinal = vaga.getDataFinal();
	        }
	    }
	    datas.put("dataInicial", dataInicial);
	    datas.put("dataFinal", dataFinal);
	    return datas;
	}

	public void inserirAgendamento(Agendamento agendamento) throws Exception {

		Optional<Solicitante> solicitante = solicitanteService.findById(agendamento.getCodigoSolicitante());
		agendamentoRepository.save(agendamento);
	}

	public void validarConsultaAgendamento(LocalDate dataInicial, LocalDate dataFinal, Long codigoSolicitante) throws Exception {

		validarCamposObrigatorios(dataInicial, dataFinal, codigoSolicitante);
		if (dataInicial.isAfter(dataFinal)) {
			throw new Exception("Data Inválida: A data inicial deve ser igual ou anterior à data final.");
		}
	}
	
	public void validarCamposObrigatorios(LocalDate dataInicial, LocalDate dataFinal, Long codigoSolicitante) throws Exception {
		if(Objects.isNull(dataInicial) && Objects.isNull(dataFinal) && Objects.isNull(codigoSolicitante)) {
			throw new Exception("Todos os campos são obrigatórios.");
		} else if(Objects.isNull(dataInicial)) {
			throw new Exception("Campo Obrigatório: A data inicial é um campo obrigatório.");
		} else if(Objects.isNull(dataFinal)) {
			throw new Exception("Campo Obrigatório: A data final é um campo obrigatório.");
		} else if(Objects.isNull(codigoSolicitante)) {
			throw new Exception("Campo Obrigatório: O código do solicitante é um campo obrigatório.");
		}
	}
	
	public List<Agendamento> findAgendamentosPorDataESolicitante(LocalDate dataInicio, LocalDate dataFim, Long codigoSolicitante) throws Exception {
		Optional<Solicitante> solicitante = solicitanteService.findById(codigoSolicitante);
		List<Agendamento> agendamentos = agendamentoRepository.findAgendamentosPorDataESolicitante(dataInicio, dataFim, codigoSolicitante);
		return agendamentos;
	}

	public AgendamentoDTO setAgendamentoDto(List<Agendamento> agendamentos, LocalDate dataInicial, LocalDate dataFinal) {

		AgendamentoDTO agendamentoDto = new AgendamentoDTO();
		agendamentoDto.setNomeSolicitante(agendamentos.get(0).getSolicitante().getNome());
		agendamentoDto.setTotalAgendamentos(agendamentos.size());
		agendamentoDto.setQuantidadeVagas(vagasService.findTotalVagasPorDataInicialEDataFinal(dataInicial, dataFinal));
		agendamentoDto.setPercentual((double) agendamentoDto.getTotalAgendamentos() / agendamentoDto.getQuantidadeVagas() * 100);
		
		return agendamentoDto;
	}
	
}
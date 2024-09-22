package com.teste.pratico.domain.managedBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.model.Agendamento;
import com.teste.pratico.domain.model.dto.AgendamentoDTO;
import com.teste.pratico.domain.service.AgendamentoService;

@ManagedBean(name = "agendamentoManagedBean")
@RequestScoped
@Component
public class AgendamentoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AgendamentoService agendamentoService;

	private Agendamento agendamento;
	private List<Agendamento> agendamentos;
	private AgendamentoDTO agendamentoDto;
	private Long codigoSolicitante;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	public AgendamentoManagedBean() {
		agendamento = new Agendamento();
	}

	public void inserirAgendamento(Agendamento agendamento) {
		try {
			agendamentoService.validarInserirAgendamento(agendamento);
			agendamentoService.inserirAgendamento(agendamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento criado com sucesso!", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	public List<Agendamento> consultarAgendamentos() {
		try {
			agendamentoService.validarConsultaAgendamento(dataInicial, dataFinal, codigoSolicitante);
			agendamentos = agendamentoService.findAgendamentosPorDataESolicitante(dataInicial, dataFinal, codigoSolicitante);
			return agendamentos;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return null;
		}
	}
	
	public AgendamentoDTO consultarQuantidadeAgendamentosPorSolicitante() {
		try {
			agendamentoService.validarConsultaAgendamento(dataInicial, dataFinal, codigoSolicitante);
			agendamentos = agendamentoService.findAgendamentosPorDataESolicitante(dataInicial, dataFinal, codigoSolicitante);
			agendamentoDto = agendamentoService.setAgendamentoDto(agendamentos, dataInicial, dataFinal);
			
			return agendamentoDto;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return null;
		}
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public AgendamentoDTO getAgendamentoDto() {
		return agendamentoDto;
	}

	public void setAgendamentoDto(AgendamentoDTO agendamentoDto) {
		this.agendamentoDto = agendamentoDto;
	}

	public Long getCodigoSolicitante() {
		return codigoSolicitante;
	}

	public void setCodigoSolicitante(Long codigoSolicitante) {
		this.codigoSolicitante = codigoSolicitante;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
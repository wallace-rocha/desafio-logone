package com.teste.pratico.domain.model.dto;

public class AgendamentoDTO {
	
	private String nomeSolicitante;
	
	private int totalAgendamentos;
	
	private int quantidadeVagas;
	
	private double percentual;

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public int getTotalAgendamentos() {
		return totalAgendamentos;
	}

	public void setTotalAgendamentos(int totalAgendamentos) {
		this.totalAgendamentos = totalAgendamentos;
	}

	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(int quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

}
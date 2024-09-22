package com.teste.pratico.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo Obrigatório: A data do agendamento é um campo obrigatório.")
    private LocalDate data;

    @NotBlank(message = "Campo Obrigatório: O número é um campo obrigatório.")
    private String numero;

    @NotBlank(message = "Campo Obrigatório: O motivo é um campo obrigatório.")
    private String motivo;
    
    @NotNull(message = "Campo Obrigatório: O código do solicitante é um campo obrigatório.")
    @Column(name = "solicitante_id")
    private Long codigoSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitante_id", nullable = false, insertable = false, updatable = false)
    private Solicitante solicitante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public Long getCodigoSolicitante() {
		return codigoSolicitante;
	}

	public void setCodigoSolicitante(Long codigoSolicitante) {
		this.codigoSolicitante = codigoSolicitante;
	}
	
}
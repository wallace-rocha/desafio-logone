package com.teste.pratico.domain.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.domain.service.SolicitanteService;

@ManagedBean(name = "solicitanteManagedBean")
@RequestScoped
@Component
public class SolicitanteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
    private Solicitante solicitante;
	
	public SolicitanteManagedBean() {
		solicitante = new Solicitante();
	}

    public void inserirSolicitante(Solicitante solicitante) {
    	solicitanteService.inserirSolicitante(solicitante);
    	FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitante criado com sucesso!", null));
    }

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

}
package com.teste.pratico.domain.managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.domain.model.Vagas;
import com.teste.pratico.domain.service.VagasService;

@ManagedBean(name = "vagasManagedBean")
@RequestScoped
@Component
public class VagasManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VagasService vagasService;

	private Vagas vaga;

	public VagasManagedBean() {
		vaga = new Vagas();
	}

	public void inserirVaga(Vagas vagas) {
		try {
			vagasService.inserirVaga(vagas);

			FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_INFO, "Vaga criada com sucesso!", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	public Vagas getVaga() {
		return vaga;
	}

	public void setVaga(Vagas vaga) {
		this.vaga = vaga;
	}

}
package com.teste.pratico.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.domain.repository.SolicitanteRepository;

@Service
public class SolicitanteService {

	@Autowired
	private SolicitanteRepository solicitanteRepository;

	public Optional<Solicitante> getSolicitanteById(Long id) {
		return solicitanteRepository.findById(id);
	}

	public void inserirSolicitante(Solicitante solicitante) {
		solicitanteRepository.save(solicitante);
	}

	public Optional<Solicitante> findById(Long codigoSolicitante) throws Exception {
		Optional<Solicitante> solicitante = solicitanteRepository.findById(codigoSolicitante);
		if (solicitante.isEmpty()) {
			throw new Exception("Solicitante Inválido: O solicitante informado não foi encontrado.");
		}
		return solicitante;
	}

}
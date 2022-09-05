package br.com.webscrapjava.brasileiraoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webscrapjava.brasileiraoapi.dto.EquipeResponseDTO;
import br.com.webscrapjava.brasileiraoapi.entity.Equipe;
import br.com.webscrapjava.brasileiraoapi.exception.NotFoundException;
import br.com.webscrapjava.brasileiraoapi.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;

	public Equipe buscarEquipeId(Long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
	}

	public EquipeResponseDTO listarEquipes() {
		EquipeResponseDTO equipes = new EquipeResponseDTO();
		equipes.setEquipes(equipeRepository.findAll());
		return equipes;
	}

}

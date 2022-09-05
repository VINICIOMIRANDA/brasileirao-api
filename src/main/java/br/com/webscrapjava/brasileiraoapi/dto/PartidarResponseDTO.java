package br.com.webscrapjava.brasileiraoapi.dto;

import java.io.Serializable;
import java.util.List;

import br.com.webscrapjava.brasileiraoapi.entity.Partida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PartidarResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Partida> partidas;

}

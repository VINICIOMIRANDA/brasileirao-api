package br.com.webscrapjava.brasileiraoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webscrapjava.brasileiraoapi.dto.PartidaGoogleDTO;
import br.com.webscrapjava.brasileiraoapi.entity.Partida;
import br.com.webscrapjava.brasileiraoapi.util.ScrapingUtil;

@Service
public class ScrappingService {
	
	@Autowired
	private ScrapingUtil scrapingUtil;
	
	@Autowired
	private PartidaService partidaService;
	
	public void verificaPartidaPeriodo() {
		Integer quantidadePartida = partidaService.buscarQuantidadePartidasPeriodo();
		if (quantidadePartida > 0) {
			List<Partida> partidas = partidaService.listarPartidasPeriodo();
			
			partidas.forEach(partida -> {
				String urlPartida = scrapingUtil
						.montaURLGoogle(partida.getEquipeCasa().getNomeEquipe(), 
								partida.getEquipeVisitante().getNomeEquipe());
				
				PartidaGoogleDTO partidaGoogle = scrapingUtil.obtemInformacaoesGoogle(urlPartida);
				
				partidaService.atualizaPartida(partida, partidaGoogle);
				
				
			});
		}
		
		
	}

}

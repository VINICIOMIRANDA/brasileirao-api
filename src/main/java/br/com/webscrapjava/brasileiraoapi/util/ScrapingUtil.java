package br.com.webscrapjava.brasileiraoapi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.webscrapjava.brasileiraoapi.dto.PartidaGoogleDTO;


@Service
public class ScrapingUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingUtil.class);

	private static final String BASE_URL_GOOGLE = "https://www.google.com/search?q=";
	private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR";
	private static final String DIV_TEMPO_PARTIDA_ANDAMENTO = "div [class=imso_mh__lv-m-stts-cont]";
	private static final String DIV_TEMPO_PARTIDA_ENCERRADA = "span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]";
	
	private static final String DIV_NOME_CAMPEONATO = "div [class=imso-hide-overflow]";
	private static final String DIV_DADOS_EQUIPE_CASA = "div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String DIV_DADOS_EQUIPE_VISITANTE = "div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]";

	private static final String ITEM_LOGO = "img[class=imso_btl__mh-logo]";
	private static final String CASA = "casa";
	private static final String VISITANTE = "visitante";
	
	private static final String DIV_PLACAR_EQUIPE_CASA = "div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]";
	private static final String DIV_PLACAR_EQUIPE_VISITANTE = "div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]";

	private static final String DIV_GOLS_EQUIPE_CASA = "div[class=imso_gs__tgs imso_gs__left-team]";
	private static final String DIV_GOLS_EQUIPE_VISITANTE = "div[class=imso_gs__tgs imso_gs__right-team]";
	private static final String ITEM_GOL = "div[class=imso_gs__gs-r]";
	private static final String DIV_PENALIDADES = "div[class=imso_mh_s__psn-sc]";

	private static final String HTTPS = "https:";
	private static final String SRC = "src";
	private static final String SPAN = "SPAN";
	private static final String PENALTIS = "Pênaltis";



	public PartidaGoogleDTO obtemInformacaoesGoogle(String url) {
		PartidaGoogleDTO partidaDTO = new PartidaGoogleDTO();

		Document document = null;

		try {
			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da pagina: {} ", title);

			StatusPartida statusPartida = obtemStatusPartida(document);
		
			partidaDTO.setStatusPartida(statusPartida);
			LOGGER.info("statusPartida : {}", statusPartida);
			
			String nomeCampeonato = recuperaNomeCampeonato(document, DIV_NOME_CAMPEONATO);
			LOGGER.info("nomeCampeonato : {}", nomeCampeonato);
			
			if (statusPartida != StatusPartida.PARTIDA_NAO_INICIADA) {
				String tempoPartida = obtemTempoPartida(document);
				partidaDTO.setTempoPartida(tempoPartida);
				LOGGER.info("TempoPartida: {}", tempoPartida);
				
				Integer placarEquipeCasa = recuperaPlacarEquipe(document, DIV_PLACAR_EQUIPE_CASA);
				partidaDTO.setPlacarEquipeCasa(placarEquipeCasa);
				LOGGER.info("Placar equipe casa: {}", placarEquipeCasa);

				Integer placarEquipeVisitante = recuperaPlacarEquipe(document, DIV_PLACAR_EQUIPE_VISITANTE);
				partidaDTO.setPlacarEquipeVisitante(placarEquipeVisitante);
				LOGGER.info("Placar equipe visitante: {}", placarEquipeVisitante);

				String golsPartidaCasa = recuperaGolsEquipe(document, DIV_GOLS_EQUIPE_CASA);
				partidaDTO.setGolsEquipeCasa(golsPartidaCasa);
				LOGGER.info("Gols equipe Casa: {}", golsPartidaCasa);

				String golsPartidaVisitante = recuperaGolsEquipe(document, DIV_GOLS_EQUIPE_VISITANTE);
				partidaDTO.setGolsEquipeVisitante(golsPartidaVisitante);
				LOGGER.info("Gols equipe Visitante: {}", golsPartidaVisitante);

				Integer placarEstendidoEquipeCasa = buscaPenalidades(document, CASA);
				partidaDTO.setPalcarEstendidoEquipeCasa(placarEstendidoEquipeCasa);
				Integer placarEstendidoEquipeVisitante = buscaPenalidades(document, VISITANTE);
				partidaDTO.setPlacarEstendidoEquipeVisitante(placarEstendidoEquipeVisitante);
				
				LOGGER.info("placar estendido Equipe Casa: {}",placarEstendidoEquipeCasa);
				LOGGER.info("placar estendido Equipe Visitante: {}", placarEstendidoEquipeVisitante);

			}

			String nomeEquipeCasa = recuperaNomeEquipe(document, DIV_DADOS_EQUIPE_CASA);
			partidaDTO.setNomeEquipeCasa(nomeEquipeCasa);
			LOGGER.info("Nome da equipe da casa: {}", nomeEquipeCasa);

			String nomeEquipeVisitante = recuperaNomeEquipe(document, DIV_DADOS_EQUIPE_VISITANTE);
			partidaDTO.setNomeEquipeVisitante(nomeEquipeVisitante);
			LOGGER.info("Nome da equipe da visitante: {}", nomeEquipeVisitante);

			String urlLogoEquipeCasa = recuperaLogoEquipe(document, DIV_DADOS_EQUIPE_CASA);
			partidaDTO.setUrLogoEquipeCasa(urlLogoEquipeCasa);
			LOGGER.info("URL logo da Equipe da Casa : {}", urlLogoEquipeCasa);

			String urlLogoEquipeVisitante = recuperaLogoEquipe(document, DIV_DADOS_EQUIPE_VISITANTE);
			partidaDTO.setUrLogoEquipeVisitante(urlLogoEquipeVisitante);
			LOGGER.info("URL logo da Equipe Visitante : {}", urlLogoEquipeVisitante);
			return partidaDTO;

		} catch (IOException e) {
			LOGGER.error("ERRO AO TENTAR CONECTAR NO GOOGLE COM JSOUP ->{}", e.getMessage());
		}
		return null;
	}



	public StatusPartida obtemStatusPartida(Document document) {

		StatusPartida statusPartida = StatusPartida.PARTIDA_NAO_INICIADA;
		boolean isTempoPartida = document.select(DIV_TEMPO_PARTIDA_ANDAMENTO).isEmpty();

		if (!isTempoPartida) {

			String tempoPartida = document.select(DIV_TEMPO_PARTIDA_ANDAMENTO).first().text();
			statusPartida = StatusPartida.PARTIDA_EM_ANDAMENTO;
			if (tempoPartida.contains(PENALTIS)) {
				statusPartida = StatusPartida.PARTIDA_PENALTIS;
			}
			LOGGER.info(tempoPartida);
		}
		isTempoPartida = document.select(DIV_TEMPO_PARTIDA_ENCERRADA).isEmpty();

		if (!isTempoPartida) {
			statusPartida = StatusPartida.PARTIDA_ENCERRADA;
		}

		LOGGER.info(statusPartida.toString());

		return statusPartida;
	}

	public String obtemTempoPartida(Document document) {
		String tempoPartida = null;
		boolean isTempoPartida = document.select(DIV_TEMPO_PARTIDA_ANDAMENTO).isEmpty();

		if (!isTempoPartida) {
			tempoPartida = document.select(DIV_TEMPO_PARTIDA_ANDAMENTO).first().text();
		}

		isTempoPartida = document.select(DIV_TEMPO_PARTIDA_ENCERRADA).isEmpty();
		if (!isTempoPartida) {
			tempoPartida = document.select(DIV_TEMPO_PARTIDA_ENCERRADA).first()
					.text();
		}

		return corrigeTempoPartida(tempoPartida);
	}

	public String corrigeTempoPartida(String tempo) {
		if (tempo.contains("'")) {
			return tempo.replace(" ", "").replace("'", " min");
		} else {
			return tempo;
		}
	}

	public String recuperaNomeEquipe(Document document, String itemHtml) {
		Element elemento = document.selectFirst(itemHtml);
		String nomeEquipe = elemento.select(SPAN).text();
		return nomeEquipe;
	}
	
	
	public String recuperaNomeCampeonato(Document document,String itemHtml) {
		Element elemento = document.selectFirst(itemHtml);
		String nomeCampeonato = elemento.select(SPAN).text();
		return nomeCampeonato;
	}

	public String recuperaLogoEquipe(Document document, String itemHtml) {
		Element elemento = document.selectFirst(itemHtml);
		String urlLogo = HTTPS + elemento.select(ITEM_LOGO).attr(SRC);
		return urlLogo;
	}

	public Integer recuperaPlacarEquipe(Document document, String itemHtml) {
		String placarEquipe = document.selectFirst(itemHtml).text();
		return formataPlacasStringInteger(placarEquipe);
	}

	public String recuperaGolsEquipe(Document document, String itemHtml) {
		List<String> golsEquipe = new ArrayList<>();
		Elements elementos = document.select(itemHtml).select(ITEM_GOL);
		for (Element e : elementos) {
			String infoGol = e.select(ITEM_GOL).text();
			golsEquipe.add(infoGol);
		}
		return String.join(", ", golsEquipe);
	}

	public Integer buscaPenalidades(Document document, String tipoEquipe) {
		boolean isPenalidades = document.select(DIV_PENALIDADES).isEmpty();
		if (!isPenalidades) {
			String penalidades = document.select(DIV_PENALIDADES).text();
			String penalidadesCompleta = penalidades.substring(0, 5).replace(" ", "");
			String[] divisao = penalidadesCompleta.split("-");
			return tipoEquipe.equals(CASA) ? formataPlacasStringInteger(divisao[0])
					: formataPlacasStringInteger(divisao[1]);
		}
		return null;

	}

	public Integer formataPlacasStringInteger(String placar) {
		Integer valor;
		try {
			valor = Integer.parseInt(placar);
		} catch (Exception e) {
			valor = 0;
		}
		return valor;
	}
	
	public String montaURLGoogle(String nomeEquipeCasa, String nomeEquipeVisitante) {
		try {
			String equipeCasa = nomeEquipeCasa.replace(" ","+").replace("-","+");
			String equipeVisitante = nomeEquipeVisitante.replace(" ","+").replace("-","+");
			
			return BASE_URL_GOOGLE +equipeCasa + "+x+"+ equipeVisitante + COMPLEMENTO_URL_GOOGLE;
			
		} catch (Exception e) {
			LOGGER.error("ERRO:{}", e.getMessage());
		}
		
		return null;
	}
}

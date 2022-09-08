package br.com.webscrapjava.brasileiraoapi.dto;

import java.io.Serializable;

import br.com.webscrapjava.brasileiraoapi.util.StatusPartida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Constructor vazio
@AllArgsConstructor // Constructor com todos os atributos
public class PartidaGoogleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StatusPartida statusPartida;
	private String tempoPartida;
	
	private String nomeEquipeCasa;
	private String urLogoEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private Integer placarEstendidoEquipeCasa;
	
	private String nomeEquipeVisitante;
	private String urLogoEquipeVisitante;
	private Integer placarEquipeVisitante;
	private String golsEquipeVisitante;
	private Integer placarEstendidoEquipeVisitante;
	
	
	public StatusPartida getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	public String getTempoPartida() {
		return tempoPartida;
	}
	public void setTempoPartida(String tempoPartida) {
		this.tempoPartida = tempoPartida;
	}
	public String getNomeEquipeCasa() {
		return nomeEquipeCasa;
	}
	public void setNomeEquipeCasa(String nomeEquipeCasa) {
		this.nomeEquipeCasa = nomeEquipeCasa;
	}
	public String getUrLogoEquipeCasa() {
		return urLogoEquipeCasa;
	}
	public void setUrLogoEquipeCasa(String urLogoEquipeCasa) {
		this.urLogoEquipeCasa = urLogoEquipeCasa;
	}
	public Integer getPlacarEquipeCasa() {
		return placarEquipeCasa;
	}
	public void setPlacarEquipeCasa(Integer placarEquipeCasa) {
		this.placarEquipeCasa = placarEquipeCasa;
	}
	public String getGolsEquipeCasa() {
		return golsEquipeCasa;
	}
	public void setGolsEquipeCasa(String golsEquipeCasa) {
		this.golsEquipeCasa = golsEquipeCasa;
	}
	public Integer getPlacarEstendidoEquipeCasa() {
		return placarEstendidoEquipeCasa;
	}
	public void setPalcarEstendidoEquipeCasa(Integer palcarEstendidoEquipeCasa) {
		this.placarEstendidoEquipeCasa = palcarEstendidoEquipeCasa;
	}
	public String getNomeEquipeVisitante() {
		return nomeEquipeVisitante;
	}
	public void setNomeEquipeVisitante(String nomeEquipeVisitante) {
		this.nomeEquipeVisitante = nomeEquipeVisitante;
	}
	public String getUrLogoEquipeVisitante() {
		return urLogoEquipeVisitante;
	}
	public void setUrLogoEquipeVisitante(String urLogoEquipeVisitante) {
		this.urLogoEquipeVisitante = urLogoEquipeVisitante;
	}
	public Integer getPlacarEquipeVisitante() {
		return placarEquipeVisitante;
	}
	public void setPlacarEquipeVisitante(Integer placarEquipeVisitante) {
		this.placarEquipeVisitante = placarEquipeVisitante;
	}
	public String getGolsEquipeVisitante() {
		return golsEquipeVisitante;
	}
	public void setGolsEquipeVisitante(String golsEquipeVisitante) {
		this.golsEquipeVisitante = golsEquipeVisitante;
	}
	public Integer getPlacarEstendidoEquipeVisitante() {
		return placarEstendidoEquipeVisitante;
	}
	public void setPlacarEstendidoEquipeVisitante(Integer placarEstendidoEquipeVisitante) {
		this.placarEstendidoEquipeVisitante = placarEstendidoEquipeVisitante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}

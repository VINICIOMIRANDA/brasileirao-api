package br.com.webscrapjava.brasileiraoapi.dto;

import java.io.Serializable;

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
	
	private String statusPartida;
	private String tempoPartida;
	
	private String nomeEquipeCasa;
	private String urLogoEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private String palcarEstendidoEquipeCasa;
	
	private String nomeEquipeVisitante;
	private String urLogoEquipeVisitante;
	private Integer placarEquipeVisitante;
	private String golsEquipeVisitante;
	private String palcarEstendidoEquipeVisitante;
	
	
	public String getStatusPartida() {
		return statusPartida;
	}
	public void setStatusPartida(String statusPartida) {
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
	public String getPalcarEstendidoEquipeCasa() {
		return palcarEstendidoEquipeCasa;
	}
	public void setPalcarEstendidoEquipeCasa(String palcarEstendidoEquipeCasa) {
		this.palcarEstendidoEquipeCasa = palcarEstendidoEquipeCasa;
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
	public String getPalcarEstendidoEquipeVisitante() {
		return palcarEstendidoEquipeVisitante;
	}
	public void setPalcarEstendidoEquipeVisitante(String palcarEstendidoEquipeVisitante) {
		this.palcarEstendidoEquipeVisitante = palcarEstendidoEquipeVisitante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}

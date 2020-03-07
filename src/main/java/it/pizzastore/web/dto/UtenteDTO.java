package it.pizzastore.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.pizzastore.model.Ruolo;
import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;

public class UtenteDTO {

	private Long id;
	@QueryParam(value = "nome")
	private String nome;
	@QueryParam(value = "cognome")
	private String cognome;
	@QueryParam(value = "username")
	private String username;
	@QueryParam(value = "dataRegistrazione")
	private Date dataRegistrazione;
	@QueryParam(value = "stato")
	private StatoUtente stato;

	@JsonIgnoreProperties(value = { "utenti" })
	private Set<Ruolo> ruoli = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public static Utente buildUtenteModelFromDTO(UtenteDTO source, boolean includeRuoli) {
		if(source == null) {
			return null;
		}
		Utente result = new Utente();
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setUsername(source.getUsername());
		result.setDataRegistrazione(source.getDataRegistrazione());
		result.setStato(source.getStato());
		if (includeRuoli) {
			result.setRuoli(source.getRuoli());
		}

		return result;
	}

	public static UtenteDTO buildUtenteDTOFromModel(Utente source, boolean includeRuoli) {
		
		if(source == null) {
			return null;
		}
		
		UtenteDTO result = new UtenteDTO();
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setUsername(source.getUsername());
		result.setDataRegistrazione(source.getDataRegistrazione());
		result.setStato(source.getStato());
		if (includeRuoli) {
			result.setRuoli(source.getRuoli());
		}

		return result;
	}

	public static List<UtenteDTO> buildDTOListFromModelList(List<Utente> input, boolean includeRuoli) {
		List<UtenteDTO> result = new ArrayList<>();
		
		for (Utente utenteItem : input) {
			UtenteDTO utenteDTOtemp = buildUtenteDTOFromModel(utenteItem, includeRuoli);
			result.add(utenteDTOtemp);
		}
		return result;
	}

}

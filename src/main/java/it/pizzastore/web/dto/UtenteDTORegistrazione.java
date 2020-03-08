package it.pizzastore.web.dto;

import javax.ws.rs.QueryParam;

import it.pizzastore.model.Utente;

public class UtenteDTORegistrazione {

	@QueryParam(value = "nome")
	private String nome;
	@QueryParam(value = "cognome")
	private String cognome;
	@QueryParam(value = "username")
	private String username;
	@QueryParam(value = "password")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Utente buildUtenteModelFromDTO(UtenteDTORegistrazione source) {
		Utente result = new Utente();
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setUsername(source.getUsername());
		result.setPassword(source.getPassword());

		return result;
	}

	public static UtenteDTORegistrazione buildUtenteDTOFromModel(Utente source) {
		UtenteDTORegistrazione result = new UtenteDTORegistrazione();
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setUsername(source.getUsername());
		result.setPassword(source.getPassword());
		return result;
	}

}

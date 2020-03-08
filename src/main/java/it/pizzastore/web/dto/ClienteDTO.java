package it.pizzastore.web.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.pizzastore.model.Cliente;
import it.pizzastore.model.Ordine;

@JsonIgnoreProperties(value = { "ordini"})
public class ClienteDTO {

	private Long id;
	@QueryParam(value = "nome")
	private String nome;
	@QueryParam(value = "cognome")
	private String cognome;
	@QueryParam(value = "via")
	private String via;
	@QueryParam(value = "citta")
	private String citta;
	@QueryParam(value = "civico")
	private String civico;
	@QueryParam(value = "telefono")
	private String telefono;

	private boolean attivo = true;

	@JsonIgnoreProperties(value = { "pizze", "utente", "cliente" })
	private Set<OrdineDTO> ordini = new HashSet<>();

	public static Cliente buildClienteModelFromDTO(ClienteDTO source, boolean includeOrdini) {
		Cliente result = new Cliente();

		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setVia(source.getVia());
		result.setCitta(source.getCitta());
		result.setCivico(source.getCivico());
		result.setTelefono(source.getTelefono());
		result.setAttivo(source.isAttivo());

		if (includeOrdini) {
			List<Ordine> tempList = OrdineDTO.buildModelListFromDTOList(new ArrayList<>(source.getOrdini()), false);
			result.getOrdini().clear();
			result.getOrdini().addAll(tempList);
		}

		return result;
	}

	public static ClienteDTO buildClienteDTOFromModel(Cliente source, boolean includeOrdini) {
		if(source == null) {
			return null;
		}
		ClienteDTO result = new ClienteDTO();

		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setVia(source.getVia());
		result.setCitta(source.getCitta());
		result.setCivico(source.getCivico());
		result.setTelefono(source.getTelefono());
		result.setAttivo(source.isAttivo());

		if (includeOrdini) {
			List<OrdineDTO> tempList = OrdineDTO.buildDTOListFromModelList(new ArrayList<>(source.getOrdini()), false);
			result.getOrdini().clear();
			result.getOrdini().addAll(tempList);
		}

		return result;
	}

	public static List<ClienteDTO> buildListClientiDTOFromModel(List<Cliente> input, boolean includeOrdini) {
		List<ClienteDTO> result = new ArrayList<>();
		if (input == null) {
			return result;
		}
		for (Cliente cliente : input) {
			result.add(ClienteDTO.buildClienteDTOFromModel(cliente, includeOrdini));
		}

		return result;
	}

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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Set<OrdineDTO> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<OrdineDTO> ordini) {
		this.ordini = ordini;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", via=" + via + ", citta=" + citta
				+ ", civico=" + civico + ", telefono=" + telefono + ", attivo=" + attivo + "]";
	}

}

package it.pizzastore.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import it.pizzastore.model.Ingrediente;

public class IngredienteDTOSearch {

	private Long id;
	@QueryParam(value="codice")
	private String codice;
	@QueryParam(value="descrizione")
	private String descrizione;
	@QueryParam(value="prezzo")
	private BigDecimal prezzo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public static Ingrediente buildIngredienteModelFromDTO(IngredienteDTOSearch source) {
		Ingrediente result = new Ingrediente();

		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzo(source.getPrezzo());

		return result;
	}

	public static List<IngredienteDTOSearch> buildListIngredientiDTOFromModel(List<Ingrediente> listAllIngredienti) {
		List<IngredienteDTOSearch> result = new ArrayList<>();

		for (Ingrediente ingredienteItem : listAllIngredienti) {
			IngredienteDTOSearch o = buildIngredienteDTOFromModel(ingredienteItem);
			result.add(o);
		}
		return result;
	}
	
	public static List<Ingrediente> buildListIngredientiModelFromDTO(List<IngredienteDTOSearch> listIngredientiDTO){
		List<Ingrediente> result = new ArrayList<>();
		
		for (IngredienteDTOSearch ingredienteItemDTO : listIngredientiDTO) {
			Ingrediente o = buildIngredienteModelFromDTO(ingredienteItemDTO);
			result.add(o);
		}
		return result;
	}

	public static IngredienteDTOSearch buildIngredienteDTOFromModel(Ingrediente source) {
		IngredienteDTOSearch result = new IngredienteDTOSearch();
		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzo(source.getPrezzo());

		return result;
	}

	@Override
	public String toString() {
		return "IngredienteDTOSearch [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", prezzo="
				+ prezzo + "]";
	}
	
	

}

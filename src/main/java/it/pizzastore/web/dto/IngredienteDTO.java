package it.pizzastore.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.pizzastore.model.Ingrediente;

public class IngredienteDTO {

	private Long id;
	private String codice;
	private String descrizione;
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

	public static Ingrediente buildIngredienteModelFromDTO(IngredienteDTO source) {
		Ingrediente result = new Ingrediente();

		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzo(source.getPrezzo());

		return result;
	}

	public static List<IngredienteDTO> buildListIngredientiDTOFromModel(List<Ingrediente> listAllIngredienti) {
		List<IngredienteDTO> result = new ArrayList<>();

		for (Ingrediente ingredienteItem : listAllIngredienti) {
			IngredienteDTO o = buildIngredienteDTOFromModel(ingredienteItem);
			result.add(o);
		}
		return result;
	}

	public static IngredienteDTO buildIngredienteDTOFromModel(Ingrediente source) {
		IngredienteDTO result = new IngredienteDTO();

		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzo(source.getPrezzo());

		return result;
	}

}

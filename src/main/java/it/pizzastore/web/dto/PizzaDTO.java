package it.pizzastore.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Pizza;

public class PizzaDTO {

	private Long id;
	@QueryParam(value = "codice")
	private String codice;
	@QueryParam(value = "descrizione")
	private String descrizione;
	@QueryParam(value = "prezzoBase")
	private BigDecimal prezzoBase;
	private boolean attivo;
	
	private List<IngredienteDTOSearch> ingredienti = new ArrayList<>();

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

	public BigDecimal getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(BigDecimal prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public List<IngredienteDTOSearch> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<IngredienteDTOSearch> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public static Pizza buildPizzaModelFromDTO(PizzaDTO source, boolean includeIngredienti) {
		Pizza result = new Pizza();
		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzoBase(source.getPrezzoBase());
		result.setAttivo(source.isAttivo());
		if (includeIngredienti) {
			List<Ingrediente> ingredienti = IngredienteDTOSearch
					.buildListIngredientiModelFromDTO(source.getIngredienti());
			result.getIngredienti().clear();
			result.getIngredienti().addAll(ingredienti);
		}

		return result;
	}

	public static PizzaDTO buildPizzaDTOFromModel(Pizza source, boolean includeIngredienti) {
		PizzaDTO result = new PizzaDTO();
		result.setId(source.getId());
		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());
		result.setPrezzoBase(source.getPrezzoBase());
		result.setAttivo(source.isAttivo());
		if (includeIngredienti) {
			List<IngredienteDTOSearch> ingredienti = IngredienteDTOSearch
					.buildListIngredientiDTOFromModel(new ArrayList<>(source.getIngredienti()));
			result.setIngredienti(ingredienti);
		}

		return result;
	}

	public static List<PizzaDTO> buildDTOListFromModelList(List<Pizza> input, boolean includeIngredienti) {
		List<PizzaDTO> result = new ArrayList<>();
		for(Pizza pizzaItem : input) {
			PizzaDTO pizzaDTOtemp = buildPizzaDTOFromModel(pizzaItem, includeIngredienti);
			result.add(pizzaDTOtemp);
		}
		return result;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

}

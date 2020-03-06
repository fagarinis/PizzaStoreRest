package it.pizzastore.web.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Pizza;

public class PizzaDTOSearch {
	
	private Long id;
	@QueryParam(value="codice")
	private String codice;
	@QueryParam(value="descrizione")
	private String descrizione;
	@QueryParam(value="prezzoBase")
	private BigDecimal prezzoBase;
	
	private Set<Ingrediente> ingredienti = new HashSet<>();

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

	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public static Pizza buildPizzaModelFromDTO(PizzaDTOSearch source) {
		return null;
	}
	
	public static Pizza buildPizzaDTOFromModel(Pizza source) {
		return null;
	}
	
	public static List<PizzaDTOSearch> buildListPizzeDTOFromModel(List<Pizza> pizze){
		return null;
	}
	
	
	
	
	

}

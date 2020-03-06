package it.pizzastore.service;

import java.util.List;
import java.util.Set;

import it.pizzastore.model.Ingrediente;

public interface IngredienteService extends IBaseService<Ingrediente> {
	
	public Set<Ingrediente> cercaIngredientiDaIdPizza(Long idPizza);

	public List<Ingrediente> findAllByListOfId(List<Long> idIngredienti);

}

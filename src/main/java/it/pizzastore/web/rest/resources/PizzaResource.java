package it.pizzastore.web.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pizzastore.model.Pizza;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.PizzaService;
import it.pizzastore.web.dto.IngredienteDTOSearch;
import it.pizzastore.web.dto.PizzaDTO;

@Component
@Path("/pizzas")
public class PizzaResource {

	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	IngredienteService ingredienteService;

//	 ## Pizza
//	 - GET /pizzas?filtriDiRicerca [pizzaiolo]
//	 - GET /pizzas/{id} [pizzaiolo]
//	 - POST /pizzas [pizzaiolo]
//	 - PUT /pizzas/{id} [pizzaiolo]
//	 - DELETE /pizzas/{id} [pizzaiolo]

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllPizzeByExample(@BeanParam PizzaDTO pizzaDTOSearch) {
		boolean includeIngredienti = true;
		Pizza example = PizzaDTO.buildPizzaModelFromDTO(pizzaDTOSearch, includeIngredienti);

		List<PizzaDTO> result = PizzaDTO.buildDTOListFromModelList(pizzaService.findByExampleEager(example),
				includeIngredienti);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPizza(@PathParam("id") Long id) {
		boolean includeIngredienti = true;
		PizzaDTO result = PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingolaPizzaConIngredienti(id), includeIngredienti);
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovaPizza(PizzaDTO pizzaDTOInput) {
		Pizza pizzaDaInserire = PizzaDTO.buildPizzaModelFromDTO(pizzaDTOInput, true);
		pizzaService.inserisciNuovo(pizzaDaInserire);
		pizzaDTOInput.setAttivo(pizzaDaInserire.isAttivo());
		pizzaDTOInput.setId(pizzaDaInserire.getId()); 
		pizzaDTOInput.setIngredienti(IngredienteDTOSearch.buildListIngredientiDTOFromModel(new ArrayList<>(ingredienteService.cercaIngredientiDaIdPizza(pizzaDaInserire.getId()))));
		return Response.status(200).entity(pizzaDTOInput).build();
	}
	
	@PUT
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePizza(@PathParam("id") Long id, PizzaDTO pizzaDTOInput) {
		pizzaDTOInput.setId(id);
		Pizza pizzaDaModificare = PizzaDTO.buildPizzaModelFromDTO(pizzaDTOInput, true);
		pizzaService.aggiornaConIngredienti(pizzaDaModificare);
		PizzaDTO pizzaModificata = PizzaDTO.buildPizzaDTOFromModel(pizzaDaModificare, true);
		pizzaModificata.setIngredienti(IngredienteDTOSearch.buildListIngredientiDTOFromModel(new ArrayList<>(ingredienteService.cercaIngredientiDaIdPizza(pizzaDaModificare.getId()))));
		
		return Response.status(200).entity(pizzaModificata).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteIngrediente(@PathParam("id") Long id) {
		Pizza pizzaDaCancellare = pizzaService.caricaSingolaPizzaConIngredienti(id);
		pizzaService.disattiva(pizzaDaCancellare);
		pizzaDaCancellare.setAttivo(false);
		PizzaDTO pizzaCancellata = PizzaDTO.buildPizzaDTOFromModel(pizzaDaCancellare, true);
		return Response.status(200).entity(pizzaCancellata).build();
	}

}

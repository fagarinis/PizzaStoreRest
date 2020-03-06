package it.pizzastore.web.rest.resources;

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

import it.pizzastore.model.Ingrediente;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.web.dto.IngredienteDTO;
import it.pizzastore.web.dto.IngredienteDTOSearch;

@Component
@Path("/ingredients")
public class IngredienteResource {

	@Autowired
	IngredienteService ingredienteService;

//		 ## Ingredienti
//		 - GET /ingredients?filtriDiRicerca [pizzaiolo]
//		 - GET /ingredients/{id} [pizzaiolo]
//		 - POST /ingredients [pizzaiolo]
//		 - PUT /ingredients/{id} [pizzaiolo] 
//		 - DELETE /ingredients/{id} [pizzaiolo]	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllIngredientiByExample(@BeanParam IngredienteDTOSearch ingredienteDTOSearch) {
		Ingrediente example = IngredienteDTOSearch.buildIngredienteModelFromDTO(ingredienteDTOSearch);

		List<IngredienteDTOSearch> result = IngredienteDTOSearch
				.buildListIngredientiDTOFromModel(ingredienteService.findByExample(example));
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIngrediente(@PathParam("id") Long id) {
		IngredienteDTO result = IngredienteDTO.buildIngredienteDTOFromModel(ingredienteService.caricaSingolo(id));
		return Response.status(200).entity(result).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoIngrediente(IngredienteDTO ingredienteDTOInput) {
		Ingrediente ingredienteDaInserire = IngredienteDTO.buildIngredienteModelFromDTO(ingredienteDTOInput);
		ingredienteService.inserisciNuovo(ingredienteDaInserire);
		ingredienteDTOInput.setId(ingredienteDaInserire.getId()); // ?? è tipo hibernate??
		return Response.status(200).entity(ingredienteDTOInput).build();
	}

	@PUT
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateIngrediente(@PathParam("id") Long id, IngredienteDTO ingredienteDTOInput) {
		ingredienteDTOInput.setId(id);
		Ingrediente ingredienteDaModificare = IngredienteDTO.buildIngredienteModelFromDTO(ingredienteDTOInput);
		ingredienteService.aggiorna(ingredienteDaModificare);
		IngredienteDTO ingredienteModificato = IngredienteDTO.buildIngredienteDTOFromModel(ingredienteDaModificare);
		return Response.status(200).entity(ingredienteModificato).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteIngrediente(@PathParam("id") Long id) {
		Ingrediente ingredienteDaCancellare = ingredienteService.caricaSingolo(id);
		ingredienteService.rimuovi(ingredienteDaCancellare);

		IngredienteDTO ingredienteCancellato = IngredienteDTO.buildIngredienteDTOFromModel(ingredienteDaCancellare);
		return Response.status(200).entity(ingredienteCancellato).build();
	}
}

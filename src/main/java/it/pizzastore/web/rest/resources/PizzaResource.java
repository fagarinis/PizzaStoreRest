package it.pizzastore.web.rest.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pizzastore.model.Pizza;
import it.pizzastore.service.PizzaService;
import it.pizzastore.web.dto.PizzaDTOSearch;

@Component
@Path("/pizzas")
public class PizzaResource {
	
	@Autowired
	PizzaService pizzaService;
	
//	 ## Pizza
//	 - GET /pizzas?filtriDiRicerca [pizzaiolo]
//	 - GET /pizzas/{id} [pizzaiolo]
//	 - POST /pizzas [pizzaiolo]
//	 - PUT /pizzas/{id} [pizzaiolo]
//	 - DELETE /pizzas/{id} [pizzaiolo]
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllPizzeByExample(@BeanParam PizzaDTOSearch pizzaDTOSearch) {
		Pizza example = PizzaDTOSearch.buildPizzaModelFromDTO(pizzaDTOSearch);

		List<PizzaDTOSearch> result = PizzaDTOSearch
				.buildListPizzeDTOFromModel(pizzaService.findByExample(example));
		return Response.status(200).entity(result).build();
	}

}

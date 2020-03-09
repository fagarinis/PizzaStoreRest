package it.pizzastore.web.rest.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
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

import it.pizzastore.model.Ordine;
import it.pizzastore.service.OrdineService;
import it.pizzastore.web.dto.OrdineDTO;

@Component
@Path("/orders")
public class OrdineResource {

	@Autowired
	OrdineService ordineService;

//		## Ordini:
//		 - GET /orders?filtriDiRicerca [admin, pizzaiolo]
//		 - POST /orders [pizzaiolo]
//		 - GET /orders/{id} [admin, pizzaiolo]
//		 - PUT /orders/{id} [pizzaiolo]
//		 - PUT /orders/{id}/close [pizzailo]

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllOrdiniByExample(@BeanParam OrdineDTO ordineDTO) {
		final boolean includePizze = true;
		Ordine example = OrdineDTO.buildOrdineModelFromDTO(ordineDTO, includePizze);
		List<Ordine> result = ordineService.findByExampleEagerOrderByData(example);
		List<OrdineDTO> resultDTO = OrdineDTO.buildDTOListFromModelList(result, includePizze);

		return Response.status(200).entity(resultDTO).build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoOrdine(OrdineDTO ordineDTOInput) {
		final boolean includePizze = true;
		Ordine ordineDaInserire = OrdineDTO.buildOrdineModelFromDTO(ordineDTOInput, includePizze);
		Ordine ordineInserito = ordineService.inserisciNuovoOrdine(ordineDaInserire);
		OrdineDTO ordineInseritoDTO = OrdineDTO.buildOrdineDTOFromModel(ordineService.caricaSingoloEager(ordineInserito.getId()), includePizze);
		return Response.status(200).entity(ordineInseritoDTO).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdine(@PathParam("id") Long id) {
		final boolean includePizze = true;
		Ordine ordineModel = ordineService.caricaSingoloEager(id);
		OrdineDTO resultDTO = OrdineDTO.buildOrdineDTOFromModel(ordineModel, includePizze);
		return Response.status(200).entity(resultDTO).build();
	}
	
	@PUT
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrdine(@PathParam("id") Long id, OrdineDTO ordineDTOInput) {
		final boolean includePizze = true;
		ordineDTOInput.setId(id);
		Ordine ordineDaAggiornare = OrdineDTO.buildOrdineModelFromDTO(ordineDTOInput, includePizze);
		Ordine ordineAggiornato = ordineService.aggiornaOrdine(ordineDaAggiornare);
		OrdineDTO ordineAggiornatoDTO = OrdineDTO.buildOrdineDTOFromModel(ordineService.caricaSingoloEager(ordineAggiornato.getId()), includePizze);
		return Response.status(200).entity(ordineAggiornatoDTO).build();
	}
	
	
	@PUT
	@Path("{id : \\d+}/close")
	@Produces(MediaType.APPLICATION_JSON)
	public Response chiudiOrdine(@PathParam("id") Long id) {
		final boolean includePizze = true;
		Ordine ordineModel = ordineService.chiudiOrdine(id);
		OrdineDTO ordineDTO = OrdineDTO.buildOrdineDTOFromModel(ordineModel, includePizze);
		return Response.status(200).entity(ordineDTO).build();
	}
}

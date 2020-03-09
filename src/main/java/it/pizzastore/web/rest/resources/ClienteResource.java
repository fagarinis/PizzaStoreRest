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

import it.pizzastore.model.Cliente;
import it.pizzastore.service.ClienteService;
import it.pizzastore.web.dto.ClienteDTO;

@Component
@Path("/clients")
public class ClienteResource {

	@Autowired
	ClienteService clienteService;

//		 ## Clienti
//		 - GET /clients?filtriDiRicerca [pizzaiolo]
//		 - GET /clients/{id} [pizzaiolo]
//		 - POST /clients [pizzaiolo]
//		 - PUT /clients/{id} [pizzaiolo]
//		 - DELETE /clients/{id} [pizzaiolo]

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllClientiByExample(@BeanParam ClienteDTO clienteDTO) {
		boolean includeOrdini = false;
		Cliente example = ClienteDTO.buildClienteModelFromDTO(clienteDTO, includeOrdini);

		List<ClienteDTO> result = ClienteDTO.buildListClientiDTOFromModel(clienteService.findByExample(example),
				includeOrdini);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCliente(@PathParam("id") Long id) {
		boolean includeOrdini = false;
		ClienteDTO result = ClienteDTO.buildClienteDTOFromModel(clienteService.caricaSingoloUtenteAttivo(id),
				includeOrdini);

		return Response.status(200).entity(result).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoCliente(@BeanParam ClienteDTO clienteDTOInput) {
		boolean includeOrdini = false;
		Cliente clienteDaInserire = ClienteDTO.buildClienteModelFromDTO(clienteDTOInput, includeOrdini);
		clienteService.inserisciNuovo(clienteDaInserire);
		clienteDTOInput.setId(clienteDaInserire.getId());
		return Response.status(200).entity(clienteDTOInput).build();
	}

	@PUT
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(@PathParam("id") Long id, @BeanParam ClienteDTO clienteDTOInput) {
		boolean includeOrdini = false;
		clienteDTOInput.setId(id);
		Cliente clienteDaModificare = ClienteDTO.buildClienteModelFromDTO(clienteDTOInput, includeOrdini);
		clienteService.aggiorna(clienteDaModificare);
		ClienteDTO clienteModificato = ClienteDTO.buildClienteDTOFromModel(clienteDaModificare, includeOrdini);

		return Response.status(200).entity(clienteModificato).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(@PathParam("id") Long id) {
		// cancellazione logica
		boolean includeOrdini = false;
		Cliente clienteDaDisattivare = clienteService.caricaSingolo(id);
		clienteService.disattiva(clienteDaDisattivare);
		ClienteDTO clienteDisattivatoDTO = ClienteDTO.buildClienteDTOFromModel(clienteDaDisattivare, includeOrdini);
		return Response.status(200).entity(clienteDisattivatoDTO).build();
	}

}

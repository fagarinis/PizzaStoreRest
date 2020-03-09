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

import it.pizzastore.model.Utente;
import it.pizzastore.service.UtenteService;
import it.pizzastore.web.dto.UtenteDTO;
import it.pizzastore.web.dto.UtenteDTORegistrazione;

@Component
@Path("/users")
public class UtenteResource {

	@Autowired
	UtenteService utenteService;

//	## Utente:
//		 - POST /users/register [anonymous]
//		 - PUT /users/{id}/activate [admin]
//		 - GET /users?fitriDiRicerca [admin]
//		 - GET /users/{id} [admin]
//		 - PUT /users/{id} [admin]

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registraUtente(@BeanParam UtenteDTORegistrazione utenteDTORegistrazione) {
		Utente utenteDaRegistrare = UtenteDTORegistrazione.buildUtenteModelFromDTO(utenteDTORegistrazione);
		Utente utenteRegistrato = utenteService.registraUtente(utenteDaRegistrare);
		UtenteDTO utenteRegistratoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteRegistrato, false);
		return Response.status(200).entity(utenteRegistratoDTO).build();
	}

	@PUT
	@Path("{id : \\d+}/activate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateUtente(@PathParam("id") Long id) {
		Utente utenteAttivato = utenteService.attivaUtenteDaId(id);
		UtenteDTO utenteAttivatoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteAttivato, true);

		return Response.status(200).entity(utenteAttivatoDTO).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUtentiByExample(@BeanParam UtenteDTO utenteDTO) {
		System.out.println(utenteDTO);
		boolean includeRuoli = true;
		Utente example = UtenteDTO.buildUtenteModelFromDTO(utenteDTO, includeRuoli);

		List<UtenteDTO> result = UtenteDTO.buildDTOListFromModelList(utenteService.findByExampleEager(example),
				includeRuoli);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUtente(@PathParam("id") Long id) {
		boolean includeRuoli = true;
		UtenteDTO result = UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtenteEager(id), includeRuoli);
		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUtente(@PathParam("id") Long id, UtenteDTO utenteDTO) {
		utenteDTO.setId(id);
		Utente utenteDaModificare = UtenteDTO.buildUtenteModelFromDTO(utenteDTO, true);
		Utente utenteModificato = utenteService.aggiornaUtenteConRuoli(utenteDaModificare);
		UtenteDTO utenteModificatoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteModificato, true);

		return Response.status(200).entity(utenteModificatoDTO).build();
	}

}

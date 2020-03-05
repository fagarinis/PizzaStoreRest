package it.pizzastore.web.rest.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pizzastore.dto.UtenteDTO;
import it.pizzastore.model.Utente;
import it.pizzastore.service.UtenteService;

@Component
@Path("/auth/login")
public class LoginResource {

	@Autowired
	UtenteService utenteService;

//	## Login:
//	- POST /auth/login [anonymous]

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response eseguiLogin(UtenteDTO utenteDTOInput) {
		Utente utenteLogin = UtenteDTO.buildModelFromDto(utenteDTOInput);
		utenteService.eseguiAccesso(utenteLogin.getUsername(), utenteLogin.getPassword());
		
		return Response.status(200).entity(utenteLogin).build();
	}

}

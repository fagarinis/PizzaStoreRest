package it.pizzastore.web.rest.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("/auth/login")
public class LoginResource {

	@Autowired
	UtenteService utenteService;

//	## Login:
//	- POST /auth/login [anonymous]

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response eseguiLogin(@BeanParam UtenteDTORegistrazione utenteDTOInput) {
		boolean includeRuoli = true;
		Utente utenteLogin = UtenteDTORegistrazione.buildUtenteModelFromDTO(utenteDTOInput);
		Utente utenteLoggato = utenteService.eseguiAccesso(utenteLogin.getUsername(), utenteLogin.getPassword());
		UtenteDTO utenteLoggatoDTO = UtenteDTO.buildUtenteDTOFromModel(utenteLoggato, includeRuoli);
		return Response.status(200).entity(utenteLoggatoDTO).build();
	}

}

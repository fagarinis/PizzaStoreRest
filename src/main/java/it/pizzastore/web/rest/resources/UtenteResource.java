package it.pizzastore.web.rest.resources;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pizzastore.service.UtenteService;

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

}

package it.pizzastore.web.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import it.pizzastore.web.rest.resources.ClienteResource;
import it.pizzastore.web.rest.resources.IngredienteResource;
import it.pizzastore.web.rest.resources.LoginResource;
import it.pizzastore.web.rest.resources.OrdineResource;
import it.pizzastore.web.rest.resources.PizzaResource;
import it.pizzastore.web.rest.resources.UtenteResource;

/*
 * questa classe non risulta sempre necessaria infatti nella configurazione attuale
 * che usa jersey potremmo farne a meno ma in alcuni casi potrebbe servire. Ad esempio
 * si ottiene il seguente messaggio:
 * 404: The origin server did not find a current representation for the target resource 
 * error when an attempt is made to invoke them
 * @author SOLVING
 *
 */
public class RestServicesConfig extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(IngredienteResource.class);
		classes.add(LoginResource.class);
		classes.add(PizzaResource.class);
		classes.add(UtenteResource.class);
		classes.add(OrdineResource.class);
		classes.add(ClienteResource.class);
		return classes;
	}
}
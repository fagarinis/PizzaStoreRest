package it.pizzastore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.CodiceRuolo;
import it.pizzastore.model.Ruolo;
import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;
import it.pizzastore.model.utils.DateUtils;
import it.pizzastore.repository.UtenteRepository;
import it.pizzastore.web.dto.StringUtils;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UtenteRepository repository;

	@Autowired
	private RuoloService ruoloService;

	@Transactional(readOnly = true)
	public List<Utente> listAll() {
		return (List<Utente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingolo(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		repository.delete(utenteInstance);

	}

	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente utenteExample) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);// Match string
																										// containing
																										// pattern
		// .withIgnoreCase();
		return (List<Utente>) repository.findAll(Example.of(utenteExample, matcher));

	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return repository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente caricaSingoloUtenteEager(long id) {
		return repository.findByIdEager(id);
	}

	@Transactional
	@Override
	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli) {
		utenteModel.getRuoli().clear();
		for (String idRuolo : listaIdRuoli) {
			Ruolo ruoloDaAggiungere = ruoloService.caricaSingolo(Long.parseLong(idRuolo));
			if (ruoloDaAggiungere != null)
				utenteModel.getRuoli().add(ruoloDaAggiungere);
		}
		aggiorna(utenteModel);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente cercaDaUsername(String username) {
		return repository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isUsernameDisponibile(String username) {
		return cercaDaUsername(username) == null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> cercaUtentiByCodiceRuoloAndCognomeLike(CodiceRuolo codiceRuolo, String cognome) {
		return repository.findUsersByRoleCodeAndSurnameLike(codiceRuolo, cognome);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> findByExampleEager(Utente example) {
		List<Utente> result = new ArrayList<>();
		String query = "select distinct u from Utente u left join fetch u.ruoli r " + " where u.id = u.id ";

		if (StringUtils.isNotBlank(example.getNome()))
			query += " and u.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotBlank(example.getCognome()))
			query += " and u.cognome like '%" + example.getCognome() + "%' ";
		if (StringUtils.isNotBlank(example.getUsername()))
			query += " and u.username like '%" + example.getUsername() + "%' ";
		if (example.getDataRegistrazione() != null)
			query += " and u.dataRegistrazione ='" + DateUtils.convertDateToSqlDateString(example.getDataRegistrazione()) + "' ";
		if (example.getStato() != null)
			query += "and u.stato = '" + example.getStato() + "' ";
		result = entityManager.createQuery(query, Utente.class).getResultList();

		return result;
	}

	/**
	 * aggiorna l'utente tranne la password e la data di registrazione
	 */
	@Transactional
	@Override
	public Utente aggiornaUtenteConRuoli(Utente utenteInstance) {
		Utente utentePersist = this.caricaSingolo(utenteInstance.getId());
		Set<Ruolo> RuoliPersist = new HashSet<>();

		for (Ruolo ruolo : utenteInstance.getRuoli()) {
			RuoliPersist.add(ruoloService.caricaSingolo(ruolo.getId()));
		}
		utentePersist.setNome(utenteInstance.getNome());
		utentePersist.setCognome(utenteInstance.getCognome());
		if (isUsernameDisponibile(utenteInstance.getUsername())) {
			utentePersist.setUsername(utenteInstance.getUsername());
		}
		utentePersist.setStato(utenteInstance.getStato());
		utentePersist.setRuoli(RuoliPersist);

		return utentePersist;
	}

	@Transactional
	@Override
	public Utente registraUtente(Utente utenteInstance) {
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setDataRegistrazione(new Date());
		inserisciNuovo(utenteInstance);
		return utenteInstance;
	}

	@Transactional
	@Override
	public Utente attivaUtenteDaId(Long id) {
		Utente utenteDaAttivare = this.caricaSingoloUtenteEager(id);
		utenteDaAttivare.setStato(StatoUtente.ATTIVO);
		return utenteDaAttivare;
	}

}

package it.pizzastore.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.pizzastore.model.Cliente;
import it.pizzastore.model.Ordine;
import it.pizzastore.model.utils.DateUtils;

@JsonIgnoreProperties(value = { "data"})
public class OrdineDTO {

	private Long id;
	
	private Date data;
	
	@QueryParam(value = "data")
	private String dataString;
	
	@QueryParam(value = "closed")
	private Boolean closed;
	@QueryParam(value = "codice")
	private String codice;
	@QueryParam(value = "costoTotale")
	private BigDecimal costoTotale;

	@JsonIgnoreProperties(value = { "ingredienti" })
	private List<PizzaDTO> pizze = new ArrayList<>();
	
	@QueryParam(value = "pizze")
	private String[] pizzeIdArray;

	@JsonIgnoreProperties(value = { "ruoli" })
	private UtenteDTO utente;

	@JsonIgnoreProperties(value = { "ordini", "attivo" })
	private Cliente cliente;

	public static Ordine buildOrdineModelFromDTO(OrdineDTO source, boolean includePizze) {
		Ordine result = new Ordine();
		result.setId(source.getId());
		result.setData(source.getData());
		result.setClosed(source.getClosed());
		result.setCodice(source.getCodice());
		result.setCostoTotale(source.getCostoTotale());
		result.setUtente(UtenteDTO.buildUtenteModelFromDTO(source.getUtente(), false));
		result.setCliente(source.getCliente());
		if (includePizze) {
			result.setPizze(PizzaDTO.buildModelListFromDTOList(source.getPizze(), false));
		}

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine source, boolean includePizze) {
		OrdineDTO result = new OrdineDTO();
		result.setId(source.getId());
		result.setData(source.getData());
		result.setClosed(source.isClosed());
		result.setCodice(source.getCodice());
		result.setCostoTotale(source.getCostoTotale());
		result.setUtente(UtenteDTO.buildUtenteDTOFromModel(source.getUtente(), false));
		result.setCliente(source.getCliente());
		if (includePizze) {
			result.setPizze(PizzaDTO.buildDTOListFromModelList(source.getPizze(), false));
		}

		return result;
	}

	public static List<OrdineDTO> buildDTOListFromModelList(List<Ordine> input, boolean includePizze) {
		List<OrdineDTO> result = new ArrayList<>();
		for (Ordine ordineItem : input) {
			result.add(OrdineDTO.buildOrdineDTOFromModel(ordineItem, includePizze));
		}

		return result;
	}

	public static List<Ordine> buildModelListFromDTOList(List<OrdineDTO> input, boolean includePizze) {
		List<Ordine> result = new ArrayList<>();
		for (OrdineDTO ordineDTOItem : input) {
			result.add(OrdineDTO.buildOrdineModelFromDTO(ordineDTOItem, includePizze));
		}

		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		Date result = null;
		if(data == null) {
			if(result == null)
				result = DateUtils.ConvertFullCETStringToDate(dataString);
			if(result == null)
				result = DateUtils.convertSimpleDateStringToDate(dataString);
			
			return result;
		}
		return data;
	}

	public void setData(Date data) {
		this.data = data;
		this.dataString = DateUtils.convertDateToFullCETDateString(data);
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public BigDecimal getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(BigDecimal costoTotale) {
		this.costoTotale = costoTotale;
	}

	public List<PizzaDTO> getPizze() {
		return pizze;
	}

	public void setPizze(List<PizzaDTO> pizze) {
		this.pizze = pizze;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "OrdineDTO [id=" + id + ", data=" + data + ", closed=" + closed + ", codice=" + codice + ", costoTotale="
				+ costoTotale + ", pizze=" + pizze +" pizzeId="+Arrays.asList(pizzeIdArray)+ ", utente=" + utente + ", cliente=" + cliente + "]";
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	
	

}

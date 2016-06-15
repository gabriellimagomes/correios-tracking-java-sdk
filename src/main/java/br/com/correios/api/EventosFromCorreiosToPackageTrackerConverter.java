package br.com.correios.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.correios.exception.DataInvalidaDoEventoException;
import br.com.correios.webservice.resource.Sroxml;

/**
 * 
 * @author Alexandre Gama
 *
 */
public class EventosFromCorreiosToPackageTrackerConverter {

	public PacoteTracker from(Sroxml eventosDoCorreios) {
		PacoteTracker pacoteTracker = new PacoteTracker();
		pacoteTracker.setQuantidade(Integer.valueOf(eventosDoCorreios.getQtd()));
		pacoteTracker.setVersao(eventosDoCorreios.getVersao());
		
		eventosDoCorreios.getObjeto().forEach(objeto -> {
			objeto.getEvento().forEach(eventoDoCorreio -> {
				Evento evento = new Evento();
				evento.setTipo(eventoDoCorreio.getTipo());
				evento.setStatus(eventoDoCorreio.getStatus());
				if (eventoDoCorreio.getData() != null && !eventoDoCorreio.getData().isEmpty()) {
					SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
					Date date;
					try {
						date = formatador.parse(eventoDoCorreio.getData());
						Calendar dataDoEvento = Calendar.getInstance();
						dataDoEvento.setTime(date);
						evento.setData(dataDoEvento);
					} catch (Exception e) {
						throw new DataInvalidaDoEventoException(String.format("A seguinte data retornada pelo correios é inválida: %s", eventoDoCorreio.getData()));
					}
				}
				evento.setHora(eventoDoCorreio.getHora());
				evento.setDescricao(eventoDoCorreio.getDescricao());
				
				LocalDoPacote localDoPacote = new LocalDoPacote(eventoDoCorreio.getLocal(), eventoDoCorreio.getCodigo(), eventoDoCorreio.getCidade(), null, eventoDoCorreio.getUf());
				evento.setLocal(localDoPacote);
				
				eventoDoCorreio.getDestino().forEach(destino -> {
					LocalDoPacote localDoDestino = new LocalDoPacote(destino.getLocal(), destino.getCodigo(), destino.getCidade(), destino.getBairro(), destino.getUf());
					Destino destinoDoPacote = new Destino(localDoDestino);
					evento.adicionaDestino(destinoDoPacote);
					
				});
				pacoteTracker.adicionaEvento(evento);
			});
		});
		return pacoteTracker;
	}

	public static void main(String[] args) {
		String horaEmString = "02:38";
		try {
			Date date = new SimpleDateFormat("hh:mm").parse(horaEmString);
			Calendar instance = Calendar.getInstance();
			instance.setTime(date);
			System.out.println(instance);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
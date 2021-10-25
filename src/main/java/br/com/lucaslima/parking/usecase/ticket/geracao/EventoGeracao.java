package br.com.lucaslima.parking.usecase.ticket.geracao;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.Evento;

/**
 * <p>
 * Interface <b>EventoGeracao</b> responsável por abstrair a implementação de eventos de
 * geração de ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public interface EventoGeracao extends Evento<Ticket> {
}

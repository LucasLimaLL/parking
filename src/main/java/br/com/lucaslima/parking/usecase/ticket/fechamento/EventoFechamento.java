package br.com.lucaslima.parking.usecase.ticket.fechamento;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.Evento;

/**
 * <p>
 * Interface <b>EventoFechamento</b> responsável por abstrair a implementação de
 * eventos de fechamento de ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public interface EventoFechamento extends Evento<Ticket> {

}

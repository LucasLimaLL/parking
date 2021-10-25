package br.com.lucaslima.parking.usecase.ticket.pagamento;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.Evento;

/**
 * <p>
 * Interface <b>EventoPagamento</b> responsável por abstrair a implementação de eventos de
 * pagamento de ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public interface EventoPagamento extends Evento<Ticket> {
}

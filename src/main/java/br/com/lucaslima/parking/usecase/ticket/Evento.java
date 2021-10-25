package br.com.lucaslima.parking.usecase.ticket;

import br.com.lucaslima.parking.domain.ticket.Ticket;

/**
 * <p>Interface Evento responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public interface Evento<T> {

    void executar(T t);
}

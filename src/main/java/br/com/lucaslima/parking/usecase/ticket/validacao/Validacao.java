package br.com.lucaslima.parking.usecase.ticket.validacao;

import br.com.lucaslima.parking.domain.ticket.Ticket;

/**
 * <p>Interface Validacao responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public interface Validacao<T> {

    void validar(T t);
}

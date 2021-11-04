package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.util.function.Predicate;

/**
 * <p>Classe TicketPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public abstract class TicketPredicate implements Predicate<Ticket> {

    public abstract String getMensagem();
}

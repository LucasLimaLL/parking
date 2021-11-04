package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.util.Objects;

/**
 * <p>Classe StatusNaoInformadoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class StatusNaoInformadoPredicate extends TicketPredicate {

    @Override
    public String getMensagem() {
        return "Status não informado.";
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param ticket the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Ticket ticket) {
        return Objects.isNull(ticket.getStatus());
    }
}

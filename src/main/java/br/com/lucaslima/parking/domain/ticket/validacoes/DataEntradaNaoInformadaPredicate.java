package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.util.Objects;

/**
 * <p>Classe DataEntradaNaoInformadaPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class DataEntradaNaoInformadaPredicate extends TicketPredicate {

    @Override
    public String getMensagem() {
        return "Data de entrada não informada.";
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
        return Objects.isNull(ticket.getEntrada());
    }
}

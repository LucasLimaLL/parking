package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;

/**
 * <p>Classe DataEntradaPosteriorDataSaidaPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class DataEntradaPosteriorDataSaidaPredicate extends TicketPredicate {
    @Override
    public String getMensagem() {
        return "Data de entrada não pode ser maior que a data de saída.";
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
        return !(ticket.getStatus() instanceof EmAbertoStatus) && ticket.getEntrada()
                                                                        .isAfter(ticket.getSaida());
    }
}

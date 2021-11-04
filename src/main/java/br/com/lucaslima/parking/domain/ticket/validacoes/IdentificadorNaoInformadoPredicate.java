package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

/**
 * <p>Classe IdentificadorNaoInformadoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class IdentificadorNaoInformadoPredicate extends TicketPredicate {

    @Override
    public String getMensagem() {
        return "Identificador não informado.";
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
        return StringUtils.isBlank(ticket.getIdentificador());
    }

}

package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.CanceladoStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Classe MotivoCancelamentoNaoInformadoTicketCanceladoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class MotivoCancelamentoNaoInformadoTicketCanceladoPredicate extends TicketPredicate {
    @Override
    public String getMensagem() {
        return "Motivo do cancelamento não informado.";
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
        return ticket.getStatus() instanceof CanceladoStatus && StringUtils.isBlank(ticket.getMotivoCancelamento());
    }
}

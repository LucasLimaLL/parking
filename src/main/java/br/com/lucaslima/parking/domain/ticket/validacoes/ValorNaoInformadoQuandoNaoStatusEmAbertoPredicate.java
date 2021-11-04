package br.com.lucaslima.parking.domain.ticket.validacoes;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;

import java.util.Objects;

/**
 * <p>Classe ValorNaoInformadoQuandoNaoStatusEmAbertoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class ValorNaoInformadoQuandoNaoStatusEmAbertoPredicate extends TicketPredicate {

    @Override
    public String getMensagem() {
        return "Valor do ticket não informado.";
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
        return !(ticket.getStatus() instanceof EmAbertoStatus) && Objects.isNull(ticket.getValor());
    }
}

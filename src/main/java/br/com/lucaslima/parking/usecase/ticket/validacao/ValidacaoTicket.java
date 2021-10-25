package br.com.lucaslima.parking.usecase.ticket.validacao;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketInvalidoException;

/**
 * <p>Classe ValidacaoTicket responsável por</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public abstract class ValidacaoTicket<Ticket> implements Validacao<Ticket> {

    @Override
    public void validar(Ticket ticket) {
        if(regra(ticket)) {
            throw new TicketInvalidoException(mensagemErro());
        }
    }

    protected abstract String mensagemErro();

    protected abstract boolean regra(Ticket ticket);
}

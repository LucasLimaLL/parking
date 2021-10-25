package br.com.lucaslima.parking.usecase.ticket.validacao;

import br.com.lucaslima.parking.domain.ticket.TicketInvalidoException;

/**
 * <p>
 * Classe <b>ValidacaoTicket</b> responsável por centralizar o método de
 * validação de tickets
 * 
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public abstract class ValidacaoTicket<Ticket> implements Validacao<Ticket> {

	/**
	 * Método que centraliza a validação
	 */
	@Override
	public void validar(Ticket ticket) {
		if (regra(ticket)) {
			throw new TicketInvalidoException(mensagemErro());
		}
	}

	protected abstract String mensagemErro();

	protected abstract boolean regra(Ticket ticket);
}

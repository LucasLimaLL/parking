package br.com.lucaslima.parking.usecase.ticket.pagamento;

/**
 * <p>
 * Exception <b>TicketNaoCalculadoException</b> responsável pelo cenário de
 * ticket não calculado
 * </p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class TicketNaoCalculadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketNaoCalculadoException(String message) {
		super(message);
	}
}

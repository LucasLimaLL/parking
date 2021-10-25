package br.com.lucaslima.parking.domain.ticket;

/**
 * <p>
 * Exception <b>TicketInvalidoException</b> responsável pelo cenário de dados
 * inválidos dos tickets
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class TicketInvalidoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an <code>IllegalArgumentException</code> with the specified detail
	 * message.
	 *
	 * @param s the detail message.
	 */
	public TicketInvalidoException(String s) {
		super(s);
	}
}

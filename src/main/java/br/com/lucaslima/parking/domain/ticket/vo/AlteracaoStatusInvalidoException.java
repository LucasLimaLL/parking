package br.com.lucaslima.parking.domain.ticket.vo;

/**
 * <p>
 * Exception <b>AlteracaoStatusInvalidoException</b> responsável pelo cenário de
 * incorreta alteração de status
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class AlteracaoStatusInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlteracaoStatusInvalidoException(String message) {
		super(message);
	}
}

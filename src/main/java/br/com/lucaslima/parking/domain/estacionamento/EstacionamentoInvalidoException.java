package br.com.lucaslima.parking.domain.estacionamento;

/**
 * <p>
 * Exception <b>EstacionamentoInvalidoException</b> responsável pelo cenário de
 * dados inválidos do Estacionamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class EstacionamentoInvalidoException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstacionamentoInvalidoException(String message) {
		super(message);
	}
}

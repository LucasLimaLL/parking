package br.com.lucaslima.parking.usecase.veiculo.buscar;

/**
 * <p>
 * Exception <b>ParametrosInvalidosException</b> responsável pelo cenário de não
 * serem informados os parametros corretamente
 * </p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class ParametrosInvalidosException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public ParametrosInvalidosException(String message) {
		super(message);
	}
}

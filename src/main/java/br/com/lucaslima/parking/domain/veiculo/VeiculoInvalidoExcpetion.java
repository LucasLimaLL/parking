package br.com.lucaslima.parking.domain.veiculo;

/**
 * <p>
 * Exception <b>VeiculoInvalidoExcpetion</b> responsável pelo cenário de dados
 * inválidos do veiculo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class VeiculoInvalidoExcpetion extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public VeiculoInvalidoExcpetion(String message) {
		super(message);
	}
}

package br.com.lucaslima.parking.usecase.estacionamento.buscar;

/**
 * <p>
 * Exception <b>NenhumEstacionamentoEncontradoException</b> responsável pelo
 * cenário de nenhum estacionamento encontrado
 * </p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class NenhumEstacionamentoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NenhumEstacionamentoEncontradoException(String message) {
		super(message);
	}
}

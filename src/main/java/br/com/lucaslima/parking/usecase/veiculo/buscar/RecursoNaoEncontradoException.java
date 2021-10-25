package br.com.lucaslima.parking.usecase.veiculo.buscar;

/**
 * <p>
 * Exception <b>RecursoNaoEncontradoException</b> responsável pelo cenário de
 * nenhum recurso encontrado
 * </p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String message) {
		super(message);
	}
}

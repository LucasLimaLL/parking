package br.com.lucaslima.parking.domain.tabelapreco;

/**
 * <p>
 * Exception <b>TabelaPrecoInvalidoException</b> responsável pelo cenário de
 * dados inválidos das tabelas de preços
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class TabelaPrecoInvalidoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public TabelaPrecoInvalidoException(String message) {
		super(message);
	}
}

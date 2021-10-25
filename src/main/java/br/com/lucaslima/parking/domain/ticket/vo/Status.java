package br.com.lucaslima.parking.domain.ticket.vo;

/**
 * <p>
 * Classe <b>Status</b> responsável por representar o status de um ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Status {

	/**
	 * Método que lança exception por o status não ser compatível com a mudança,
	 * esse método é sobrescrito nas classes filhas dado a sua necessidade
	 */
	public Status pagar() {
		return lancarException("Aguardando Pagamento");
	}

	/**
	 * Método que lança exception por o status não ser compatível com a mudança,
	 * esse método é sobrescrito nas classes filhas dado a sua necessidade
	 */
	public Status finalizar() {
		return lancarException("Finalizado");
	}

	/**
	 * Método que lança exception por o status não ser compatível com a mudança,
	 * esse método é sobrescrito nas classes filhas dado a sua necessidade
	 */
	public Status cancelar() {
		return lancarException("Cancelado");
	}

	/**
	 * Método que lança a exception
	 */
	private Status lancarException(String nomeStatus) {
		throw new AlteracaoStatusInvalidoException(String.format("Ticket não pode ir para status '%s'", nomeStatus));
	}
}

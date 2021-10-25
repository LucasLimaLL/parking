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

	public Status pagar() {
		return lancarException("Aguardando Pagamento");
	}

	public Status finalizar() {
		return lancarException("Finalizado");
	}

	public Status cancelar() {
		return lancarException("Cancelado");
	}

	private Status lancarException(String nomeStatus) {
		throw new AlteracaoStatusInvalidoException(String.format("Ticket não pode ir para status '%s'", nomeStatus));
	}
}

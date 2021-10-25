package br.com.lucaslima.parking.domain.ticket.vo;

/**
 * <p>
 * Classe <b>AguardandoPagamentoStatus</b> responsável por representar o status
 * de Aguardando Pagamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class AguardandoPagamentoStatus extends Status {

	@Override
	public Status finalizar() {
		return new FinalizadoStatus();
	}

	@Override
	public Status cancelar() {
		return new CanceladoStatus();
	}
}

package br.com.lucaslima.parking.usecase.ticket.cancelamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;

/**
 * <p>
 * Classe <b>CancelaTicketUseCase</b> responsável pelo cenário de cancelamento
 * de tickets
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class CancelaTicketUseCase {

	private List<EventoCancelamento> eventosCancelamento;

	public CancelaTicketUseCase(List<EventoCancelamento> eventosCancelamento) {
		this.eventosCancelamento = eventosCancelamento;
	}

	/**
	 * Método que realiza o cancelamento do ticket
	 * 
	 * @param ticket             - ticket a ser cancelamento
	 * @param motivoCancelamento - texto com o motivo do cancelamento
	 * @param dataCancelamento   - data efetiva do cancelamento
	 * @return ticket cancelado
	 */
	public Ticket cancelar(Ticket ticket, String motivoCancelamento, LocalDateTime dataCancelamento) {

		if (Objects.isNull(ticket) || StringUtils.isBlank(motivoCancelamento) || Objects.isNull(dataCancelamento)) {
			throw new ParametrosInvalidosException(
					"Ticket, motivo do cancelamento ou data do cancelamento não " + "informados.");
		}

		ticket.cancelar(motivoCancelamento, dataCancelamento);

		eventosCancelamento.forEach(evento -> evento.executar(ticket));

		return ticket;
	}
}

package br.com.lucaslima.parking.usecase.ticket.fechamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;

/**
 * <p>
 * Classe <b>FinalizaTicketUseCase</b> responsável pelo cenário de fechamento de
 * tickets
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class FinalizaTicketUseCase {

	private List<EventoFechamento> eventosFechamento;

	public FinalizaTicketUseCase(List<EventoFechamento> eventosFechamento) {
		this.eventosFechamento = eventosFechamento;
	}

	/**
	 * Método que realiza o fechamento do ticket
	 * 
	 * @param ticket         - ticket a ser fechado
	 * @param dataFechamento - data de fechamento do ticket
	 * @return Ticket finalizado
	 */
	public Ticket fechar(Ticket ticket, LocalDateTime dataFechamento) {

		if (Objects.isNull(ticket) || Objects.isNull(dataFechamento)) {
			throw new ParametrosInvalidosException("Ticket ou data do fechamento não informados.");
		}

		ticket.finalizar(dataFechamento);

		eventosFechamento.forEach(evento -> evento.executar(ticket));

		return ticket;
	}
}

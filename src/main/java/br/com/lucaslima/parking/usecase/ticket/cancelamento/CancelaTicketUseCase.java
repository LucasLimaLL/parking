package br.com.lucaslima.parking.usecase.ticket.cancelamento;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.Evento;
import br.com.lucaslima.parking.usecase.veiculo.ParametrosInvalidosException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>Classe CancelaTicketUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public class CancelaTicketUseCase {

    private List<EventoCancelamento> eventosCancelamento;

    public CancelaTicketUseCase(
            List<EventoCancelamento> eventosCancelamento) {
        this.eventosCancelamento = eventosCancelamento;
    }

    public Ticket cancelar(Ticket ticket, String motivoCancelamento, LocalDateTime dataCancelamento) {

        if (Objects.isNull(ticket) || StringUtils.isBlank(motivoCancelamento) || Objects.isNull(dataCancelamento)) {
            throw new ParametrosInvalidosException("Ticket, motivo do cancelamento ou data do cancelamento não " +
                                                   "informados.");
        }

        ticket.cancelar(motivoCancelamento, dataCancelamento);

        eventosCancelamento.forEach(evento -> evento.executar(ticket));

        return ticket;
    }
}

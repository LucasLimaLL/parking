package br.com.lucaslima.parking.usecase.ticket.fechamento;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.Evento;
import br.com.lucaslima.parking.usecase.veiculo.ParametrosInvalidosException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>Classe CancelaTicketUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public class FinalizaTicketUseCase {

    private List<EventoFechamento> eventosFechamento;

    public FinalizaTicketUseCase(
            List<EventoFechamento> eventosFechamento) {
        this.eventosFechamento = eventosFechamento;
    }

    public Ticket fechar(Ticket ticket, LocalDateTime dataFechamento) {

        if (Objects.isNull(ticket) || Objects.isNull(dataFechamento)) {
            throw new ParametrosInvalidosException("Ticket ou data do fechamento não " +
                                                   "informados.");
        }

        ticket.finalizar(dataFechamento);

        eventosFechamento.forEach(evento -> evento.executar(ticket));

        return ticket;
    }
}

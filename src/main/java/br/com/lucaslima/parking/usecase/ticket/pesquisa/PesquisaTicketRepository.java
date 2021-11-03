package br.com.lucaslima.parking.usecase.ticket.pesquisa;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.Status;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Classe PesquisaTicketRepository responsável por</p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public interface PesquisaTicketRepository {

    List<Ticket> buscar(Estacionamento estacionamento, LocalDateTime periodoInicio, LocalDateTime periodoFim,
                        List<Status> status);

    Ticket buscar(String identificador);
}

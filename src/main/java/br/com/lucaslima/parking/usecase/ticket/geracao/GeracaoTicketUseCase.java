package br.com.lucaslima.parking.usecase.ticket.geracao;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.usecase.ticket.validacao.ValidacaoTicket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>Classe GeracaoTicketUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public class GeracaoTicketUseCase {

    private List<ValidacaoTicket> validacoes;
    private List<EventoGeracao> eventosGeracao;

    public GeracaoTicketUseCase(
            List<ValidacaoTicket> validacoes,
            List<EventoGeracao> eventosGeracao) {
        this.validacoes = validacoes;
        this.eventosGeracao = eventosGeracao;
    }

    public Ticket gerar(Estacionamento estacionamento, Veiculo veiculo) {

        Ticket ticket = new TicketBuilder()
                .identificador(UUID.randomUUID()
                                   .toString())
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now())
                .status(new EmAbertoStatus())
                .build();

        this.validacoes.forEach(validacao -> validacao.validar(ticket));
        this.eventosGeracao.forEach(evento -> evento.executar(ticket));
        
        return ticket;
    }
}

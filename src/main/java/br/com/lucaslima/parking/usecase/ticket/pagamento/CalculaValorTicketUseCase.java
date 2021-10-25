package br.com.lucaslima.parking.usecase.ticket.pagamento;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios.CalculoTempo15Minutos;
import br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios.CalculoTempo1Hora;
import br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios.CalculoTempoDiaria;
import br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios.CalculoTempoNHoras;
import br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios.CalculoTempoTolerancia;
import br.com.lucaslima.parking.usecase.veiculo.ParametrosInvalidosException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>Classe CalculaValorTicketUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class CalculaValorTicketUseCase {

    private final List<EventoPagamento> eventosPagamento;
    private final BuscaTabelaPrecoRepository tabelaPrecoRepository;
    private final CalculoTempoTolerancia cenarioTolerancia = new CalculoTempoTolerancia(null);
    private final CalculoTempo15Minutos cenario15Minutos = new CalculoTempo15Minutos(cenarioTolerancia);
    private final CalculoTempo1Hora cenario1Hora = new CalculoTempo1Hora(cenario15Minutos);
    private final CalculoTempoNHoras cenarioNHoras = new CalculoTempoNHoras(cenario1Hora);
    private final CalculoTempoDiaria cenarioTempoDiaria = new CalculoTempoDiaria(cenarioNHoras);

    public CalculaValorTicketUseCase(BuscaTabelaPrecoRepository tabelaPrecoRepository, List<EventoPagamento> eventos) {
        this.tabelaPrecoRepository = tabelaPrecoRepository;
        this.eventosPagamento = eventos;
    }

    public Ticket calcularValor(Ticket ticket, LocalDateTime dataFechamento) {

        if (Objects.isNull(ticket) || Objects.isNull(dataFechamento)) {
            throw new ParametrosInvalidosException("Ticket ou data de fechamento não informados.");
        }

        TabelaPreco tabelaPreco = this.tabelaPrecoRepository.buscar(ticket.getEstacionamento(), ticket.getVeiculo());

        if (Objects.isNull(tabelaPreco)) {
            throw new TicketNaoCalculadoException("Tabela de preço não cadastrada.");
        }

        ticket.informarSaida(dataFechamento);
        ticket.calcularEstadia();

        ticket.setValor(cenarioTempoDiaria.validar(ticket, tabelaPreco));

        this.eventosPagamento.forEach(evento -> evento.executar(ticket));
        return ticket;
    }
}

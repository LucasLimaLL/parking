package br.com.lucaslima.parking.usecase.ticket.pesquisa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.AguardandoPagamentoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

@ExtendWith(MockitoExtension.class)
class PesquisaTicketUseCaseTest {

    @Mock
    private PesquisaTicketRepository pesquisaTicketRepository;

    private PesquisaTicketUseCase pesquisaTicketUseCase;

    private Estacionamento estacionamento = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
            .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
            .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
            .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
            .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(17, 0))
            .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0), LocalTime.of(13, 0))
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();
    private Veiculo veiculo = new VeiculoBuilder()
            .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
            .placa("AAA0000")
            .marca("FIAT")
            .modelo("UNO")
            .build();
    private Ticket ticket1 = new TicketBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .estacionamento(estacionamento)
            .veiculo(veiculo)
            .entrada(LocalDateTime.now()
                                  .minus(1, ChronoUnit.HOURS))
            .saida(LocalDateTime.now())
            .valor(BigDecimal.ZERO)
            .estadia(Duration.ofHours(1))
            .status(new AguardandoPagamentoStatus())
            .build();
    private Ticket ticket2 = new TicketBuilder()
            .identificador("11111111-1111-1111-1111-111111111111")
            .estacionamento(estacionamento)
            .veiculo(veiculo)
            .entrada(LocalDateTime.now()
                                  .minus(1, ChronoUnit.HOURS))
            .saida(LocalDateTime.now())
            .valor(BigDecimal.ZERO)
            .estadia(Duration.ofHours(1))
            .status(new AguardandoPagamentoStatus())
            .build();


    @BeforeEach
    void setUp() {
        pesquisaTicketUseCase = new PesquisaTicketUseCase(Collections.emptyList(), pesquisaTicketRepository);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testBuscarPorParametrosEstacionamentoPeriodosStatus() {
        when(pesquisaTicketRepository.buscar(Mockito.any(Estacionamento.class), Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class), Mockito.any(List.class))).thenReturn(List.of(ticket1, ticket2));
        List<Ticket> tickets =
                this.pesquisaTicketUseCase.buscar(estacionamento, LocalDateTime.now(), LocalDateTime.now(),
                        Collections.emptyList());

        assertNotNull(tickets);
        assertEquals(2, tickets.size());
    }

}
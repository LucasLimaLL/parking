package br.com.lucaslima.parking.usecase.ticket.pagamento;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.tabelapreco.TabelaPrecoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculaValorTicketUseCaseTest {

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
    private TabelaPreco tabela = new TabelaPrecoBuilder()
            .quinzeMinutos(BigDecimal.valueOf(2))
            .umaHora(BigDecimal.valueOf(8))
            .horaAdicional(BigDecimal.valueOf(5))
            .diaria(BigDecimal.valueOf(20))
            .build();
    @Mock
    private BuscaTabelaPrecoRepository buscaTabelaPrecoRepository;

    private CalculaValorTicketUseCase calculaValorTicketUseCase;

    @BeforeEach
    void setUp() {
        calculaValorTicketUseCase = new CalculaValorTicketUseCase(buscaTabelaPrecoRepository, Collections.emptyList());
        when(buscaTabelaPrecoRepository.buscar(Mockito.any(), Mockito.any())).thenReturn(tabela);
    }

    @Test
    void testCalcularCenarioTolerancia() {

        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(3, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.ZERO, ticket.getValor());
    }

    @Test
    void testCalcularCenario15Minutos() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(16, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(2), ticket.getValor());
    }

    @Test
    void testCalcularCenario1Hora() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(61, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(8), ticket.getValor());
    }

    @Test
    void testCalcularCenario2Horas() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(121, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(13), ticket.getValor());
    }

    @Test
    void testCalcularCenario4Horas() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(181, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(18), ticket.getValor());
    }

    @Test
    void testCalcularCenario6Horas() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(361, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(33), ticket.getValor());
    }

    @Test
    void testCalcularCenario8Horas() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(481, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(20), ticket.getValor());
    }

    @Test
    void testCalcularCenario10Horas() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(601, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(20), ticket.getValor());
    }

    @Test
    void testCalcularCenario1Dia() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1441, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(20), ticket.getValor());
    }

    @Test
    void testCalcularCenario2Dias() {
        Ticket ticket = new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(2881, ChronoUnit.MINUTES))
                .status(new EmAbertoStatus())
                .build();
        calculaValorTicketUseCase.calcularValor(ticket, LocalDateTime.now());

        assertNotNull(ticket.getSaida());
        assertEquals(BigDecimal.valueOf(40), ticket.getValor());
    }

    @Test
    void testCalcularValorSemTabelaPreco() {
        when(buscaTabelaPrecoRepository.buscar(Mockito.any(Estacionamento.class),
                Mockito.any(Veiculo.class))).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            calculaValorTicketUseCase.calcularValor(new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .status(new EmAbertoStatus())
                    .build(), LocalDateTime.now());
        });

        assertTrue(exception instanceof TicketNaoCalculadoException);
        assertEquals("Tabela de preço não cadastrada.", exception.getLocalizedMessage());

    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void testCalcularValorComParametrosInvalidos() {
        Exception exception = assertThrows(Exception.class, () -> {
            calculaValorTicketUseCase.calcularValor(null, null);
        });

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket ou data de fechamento não informados.", exception.getLocalizedMessage());
    }
}
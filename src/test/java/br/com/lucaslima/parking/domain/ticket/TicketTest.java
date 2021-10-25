package br.com.lucaslima.parking.domain.ticket;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.AguardandoPagamentoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.AlteracaoStatusInvalidoException;
import br.com.lucaslima.parking.domain.ticket.vo.CanceladoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.FinalizadoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

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

    @Test
    void testConstrutorSucessoComStatusEmAberto() {
        assertDoesNotThrow(() -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new EmAbertoStatus())
                .build());
    }

    @Test
    void testConstrutorSucessoComStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());
    }

    @Test
    void testConstrutorSucessoComStatusCancelado() {
        assertDoesNotThrow(() -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .dataCancelamento(LocalDateTime.now())
                .motivoCancelamento("Motivo qualquer.")
                .status(new CanceladoStatus())
                .build());
    }

    @Test
    void testConstrutorSucessoComStatusFinalizado() {
        assertDoesNotThrow(() -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .estadia(Duration.ofHours(1))
                .dataFinalizado(LocalDateTime.now())
                .status(new FinalizadoStatus())
                .build());
    }

    @Test
    void testConstrutorFalhaSemIdentificador() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Identificador não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaSemEstacionamento() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Estacionamento não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaSemVeiculo() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Veículo não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaSemDataEntrada() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de entrada não informada.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaSemDataSaida() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de saída não informada.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaComDataDeEntradaPosteriorASaida() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .plus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .status(new AguardandoPagamentoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de entrada não pode ser maior que a data de saída.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaTicketCancelamentoSemMotivoDeCancelamentoInformado() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .dataCancelamento(LocalDateTime.now())
                .status(new CanceladoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Motivo do cancelamento não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaTicketCancelamentoSemDataDeCancelamentoInformado() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .motivoCancelamento("Motivo qualquer.")
                .status(new CanceladoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de cancelamento não informada.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorFalhaTicketFinalizadoSemDataDeFechamentoInformada() {
        Exception exception = assertThrows(Exception.class, () -> new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamento)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .saida(LocalDateTime.now())
                .estadia(Duration.ofHours(1))
                .status(new FinalizadoStatus())
                .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de fechamento não informada.", exception.getLocalizedMessage());
    }

    @Test
    void testPagarSucessoStatusEmAberto() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .status(new EmAbertoStatus())
                    .build();

            ticket.calcularEstadia();
        });
    }

    @Test
    void testPagarFalhaStatusAguardandoPagamento() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .status(new AguardandoPagamentoStatus())
                    .build();

            ticket.calcularEstadia();
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Aguardando Pagamento'", exception.getLocalizedMessage());
    }

    @Test
    void testPagarFalhaStatusCancelado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .motivoCancelamento("Motivo de cancelamento qualquer.")
                    .dataCancelamento(LocalDateTime.now())
                    .status(new CanceladoStatus())
                    .build();

            ticket.calcularEstadia();
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Aguardando Pagamento'", exception.getLocalizedMessage());
    }

    @Test
    void testPagarFalhaStatusFinalizado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .dataFinalizado(LocalDateTime.now())
                    .status(new FinalizadoStatus())
                    .build();

            ticket.calcularEstadia();
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Aguardando Pagamento'", exception.getLocalizedMessage());
    }

    @Test
    void testFinalizarSucessoStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .status(new AguardandoPagamentoStatus())
                    .build();

            ticket.finalizar(LocalDateTime.now());
        });
    }

    @Test
    void testFinalizarFalhaStatusEmAberto() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .status(new EmAbertoStatus())
                    .build();

            ticket.finalizar(LocalDateTime.now());
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Finalizado'", exception.getLocalizedMessage());
    }

    @Test
    void testFinalizarFalhaStatusCancelado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .dataCancelamento(LocalDateTime.now())
                    .motivoCancelamento("Motivo de cancelamento qualquer.")
                    .status(new CanceladoStatus())
                    .build();

            ticket.finalizar(LocalDateTime.now());
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Finalizado'", exception.getLocalizedMessage());
    }

    @Test
    void testFinalizarFalhaStatusFinalizado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .dataFinalizado(LocalDateTime.now())
                    .status(new FinalizadoStatus())
                    .build();

            ticket.finalizar(LocalDateTime.now());
        });

        assertTrue(exception instanceof AlteracaoStatusInvalidoException);
        assertEquals("Ticket não pode ir para status 'Finalizado'", exception.getLocalizedMessage());
    }

    @Test
    void testCancelarSucessoStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder()
                    .identificador("00000000-0000-0000-0000-000000000000")
                    .estacionamento(estacionamento)
                    .veiculo(veiculo)
                    .entrada(LocalDateTime.now()
                                          .minus(1, ChronoUnit.HOURS))
                    .saida(LocalDateTime.now())
                    .status(new AguardandoPagamentoStatus())
                    .build();

            ticket.cancelar("Motivo qualquer para cancelamento.", LocalDateTime.now());
        });
    }
}
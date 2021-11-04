package br.com.lucaslima.parking.usecase.ticket.fechamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.AguardandoPagamentoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.FinalizadoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;

class FinalizaTicketUseCaseTest {
    private FinalizaTicketUseCase finalizaTicketUseCase = new FinalizaTicketUseCase(Collections.emptyList());

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
    private Ticket ticket = new TicketBuilder()
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

    @Test
    void testFecharSucesso() {
        assertTrue(ticket.getStatus() instanceof AguardandoPagamentoStatus);
        assertNull(ticket.getDataFinalizado());
        ticket = this.finalizaTicketUseCase.fechar(ticket, LocalDateTime.now());
        assertTrue(ticket.getStatus() instanceof FinalizadoStatus);
        assertNotNull(ticket.getDataFinalizado());
    }

    @Test
    void testFecharSemInformarTicket() {
        Exception exception = assertThrows(Exception.class, () ->
                        this.finalizaTicketUseCase.fechar(null, LocalDateTime.now())
                                          );

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket ou data do fechamento não informados.",
                exception.getLocalizedMessage());
    }

    @Test
    void testFecharSemInformarDataCancelamento() {
        Exception exception = assertThrows(Exception.class, () ->
                        this.finalizaTicketUseCase.fechar(ticket, null)
                                          );

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket ou data do fechamento não informados.",
                exception.getLocalizedMessage());
    }
}
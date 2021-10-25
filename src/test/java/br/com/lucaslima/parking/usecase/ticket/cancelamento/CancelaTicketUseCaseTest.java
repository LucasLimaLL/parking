package br.com.lucaslima.parking.usecase.ticket.cancelamento;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.AguardandoPagamentoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.CanceladoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.veiculo.ParametrosInvalidosException;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CancelaTicketUseCaseTest {

    private CancelaTicketUseCase cancelaTicketUseCase = new CancelaTicketUseCase(Collections.emptyList());

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
            .status(new AguardandoPagamentoStatus())
            .build();

    @Test
    void testCancelarSucesso() {
        assertTrue(ticket.getStatus() instanceof AguardandoPagamentoStatus);
        assertNull(ticket.getDataCancelamento());
        assertNull(ticket.getMotivoCancelamento());
        ticket = this.cancelaTicketUseCase.cancelar(ticket, "Motivo qualquer.", LocalDateTime.now());
        assertTrue(ticket.getStatus() instanceof CanceladoStatus);
        assertNotNull(ticket.getDataCancelamento());
        assertNotNull(ticket.getMotivoCancelamento());
    }

    @Test
    void testCancelarSemInformarTicket() {
        Exception exception = assertThrows(Exception.class, () ->
                        this.cancelaTicketUseCase.cancelar(null, "Motivo qualquer.", LocalDateTime.now())
                                          );

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket, motivo do cancelamento ou data do cancelamento não informados.",
                exception.getLocalizedMessage());
    }

    @Test
    void testCancelarSemInformarMotivoCancelamento() {
        Exception exception = assertThrows(Exception.class, () ->
                        this.cancelaTicketUseCase.cancelar(ticket, null, LocalDateTime.now())
                                          );

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket, motivo do cancelamento ou data do cancelamento não informados.",
                exception.getLocalizedMessage());
    }

    @Test
    void testCancelarSemInformarDataCancelamento() {
        Exception exception = assertThrows(Exception.class, () ->
                        this.cancelaTicketUseCase.cancelar(ticket, "Motivo qualquer.", null)
                                          );

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Ticket, motivo do cancelamento ou data do cancelamento não informados.",
                exception.getLocalizedMessage());
    }
}
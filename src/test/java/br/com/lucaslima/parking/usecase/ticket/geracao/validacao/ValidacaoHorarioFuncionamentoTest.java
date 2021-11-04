package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.TicketInvalidoException;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.ticket.validacao.ValidacaoTicket;

class ValidacaoHorarioFuncionamentoTest {

    private ValidacaoTicket<Ticket> validacao = new ValidacaoHorarioFuncionamento();

    private Estacionamento estacionamentoHoraDentro = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now().getDayOfWeek(), LocalDateTime.now().minus(1, ChronoUnit.HOURS).toLocalTime()
                    , LocalDateTime.now().plus(1, ChronoUnit.HOURS).toLocalTime())
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();

    private Estacionamento estacionamentoHoraFora = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now().getDayOfWeek(),
                    LocalDateTime.now().plus(1, ChronoUnit.HOURS).toLocalTime()
                    , LocalDateTime.now().plus(2, ChronoUnit.HOURS).toLocalTime())
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
    void testRegraHorarioFuncionamentoCenarioQueEntraNaRegra() {
        assertDoesNotThrow(() -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoHoraDentro)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now())
                .status(new EmAbertoStatus())
                .build()));
    }

    @Test
    void testRegraHorarioFuncionamentoCenarioQueNaoEntraNaRegra() {
        Exception exception = assertThrows(Exception.class, () -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoHoraFora)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now())
                .status(new EmAbertoStatus())
                .build()));

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Entrada fora do horário de funcionamento.", exception.getLocalizedMessage());
    }
}
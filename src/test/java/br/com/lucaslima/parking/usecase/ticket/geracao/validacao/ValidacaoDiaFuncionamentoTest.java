package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.TicketInvalidoException;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.ticket.validacao.Validacao;

class ValidacaoDiaFuncionamentoTest {

    private Estacionamento estacionamentoDiaDentro = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now().getDayOfWeek(), LocalTime.of(8, 0), LocalTime.of(8, 0))
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();

    private Estacionamento estacionamentoDiaFora = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000- 0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now().plus(2, ChronoUnit.DAYS).getDayOfWeek(), LocalTime.of(8, 0), LocalTime.of(8, 0))
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();

    private Veiculo veiculo = new VeiculoBuilder()
            .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
            .placa("AAA0000")
            .marca("FIAT")
            .modelo("UNO")
            .build();

    private Validacao validacao = new ValidacaoDiaFuncionamento();

    @Test
    void testRegraHorarioFuncionamentoCenarioQueNaoEntraNaRegra() {

        assertDoesNotThrow(() -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoDiaDentro)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new EmAbertoStatus())
                .build()));
    }

    @Test
    void testRegraHorarioFuncionamentoCenarioQueEntraNaRegra() {
        Exception exception = assertThrows(Exception.class, () -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoDiaFora)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new EmAbertoStatus())
                .build()));

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Entrada foi informado em dia de não funcionamento.", exception.getLocalizedMessage());
    }
}
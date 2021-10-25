package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.TicketInvalidoException;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.ticket.validacao.Validacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class ValidacaoOcupacaoEstacionamentoTest {

    private Estacionamento estacionamentoVazio = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now()
                                               .getDayOfWeek(), LocalTime.of(8, 0), LocalTime.of(8, 0))
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();

    private Estacionamento estacionamentoLotado = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now()
                                               .plus(2, ChronoUnit.DAYS)
                                               .getDayOfWeek(), LocalTime.of(8, 0), LocalTime.of(8, 0))
            .quantidadeAtual(50)
            .lotacaoMaxima(50)
            .build();

    private Veiculo veiculo = new VeiculoBuilder()
            .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
            .placa("AAA0000")
            .marca("FIAT")
            .modelo("UNO")
            .build();

    private Validacao validacao = new  ValidacaoOcupacaoEstacionamento();

    @Test
    void testRegraDiaFuncionamentoCenarioQueNaoEntraNaRegra() {

        assertDoesNotThrow(() -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoVazio)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new EmAbertoStatus())
                .build()));
    }

    @Test
    void testRegraDiaFuncionamentoCenarioQuentraNaRegra() {
        Exception exception = assertThrows(Exception.class, () -> validacao.validar(new TicketBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .estacionamento(estacionamentoLotado)
                .veiculo(veiculo)
                .entrada(LocalDateTime.now()
                                      .minus(1, ChronoUnit.HOURS))
                .status(new EmAbertoStatus())
                .build()));

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Estacionamento lotado.", exception.getLocalizedMessage());
    }
}
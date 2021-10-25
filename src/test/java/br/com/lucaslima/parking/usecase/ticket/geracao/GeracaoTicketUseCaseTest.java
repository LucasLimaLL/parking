package br.com.lucaslima.parking.usecase.ticket.geracao;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;
import br.com.lucaslima.parking.domain.veiculo.VeiculoBuilder;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import br.com.lucaslima.parking.usecase.ticket.geracao.validacao.ValidacaoDiaFuncionamento;
import br.com.lucaslima.parking.usecase.ticket.geracao.validacao.ValidacaoHorarioFuncionamento;
import br.com.lucaslima.parking.usecase.ticket.geracao.validacao.ValidacaoOcupacaoEstacionamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeracaoTicketUseCaseTest {
    private Estacionamento estacionamento = new EstacionamentoBuilder()
            .identificador("00000000-0000-0000-0000-000000000000")
            .nome("Estacionamento Teste")
            .horarioFuncionamento(LocalDateTime.now()
                                               .getDayOfWeek(),
                    LocalDateTime.now()
                                 .minus(1, ChronoUnit.HOURS)
                                 .toLocalTime(), LocalDateTime.now()
                                                              .plus(1, ChronoUnit.HOURS)
                                                              .toLocalTime())
            .quantidadeAtual(0)
            .lotacaoMaxima(50)
            .build();


    private Veiculo veiculo = new VeiculoBuilder()
            .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
            .placa("AAA0000")
            .marca("FIAT")
            .modelo("UNO")
            .build();

    private GeracaoTicketUseCase geracaoTicketUseCase =
            new GeracaoTicketUseCase(List.of(
                    new ValidacaoDiaFuncionamento(),
                    new ValidacaoHorarioFuncionamento(),
                    new ValidacaoOcupacaoEstacionamento()), Collections.emptyList());

    @Test
    void testGerarSucesso() {
        Ticket ticket = this.geracaoTicketUseCase.gerar(estacionamento, veiculo);
        assertTrue(ticket.getStatus() instanceof EmAbertoStatus);
    }
}
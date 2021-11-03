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
    private Veiculo veiculo = new VeiculoBuilder().tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                                                  .placa("AAA0000")
                                                  .marca("FIAT")
                                                  .modelo("UNO")
                                                  .build();

    /**
     * <p>
     * Método <b>testConstrutorSucessoComStatusEmAberto</b> responsável por testar o
     * cenário de sucesso no construtor no cenário de ticket em aberto
     * </p>
     **/
    @Test
    void testConstrutorSucessoComStatusEmAberto() {
        assertDoesNotThrow(() -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                                    .estacionamento(estacionamento)
                                                    .veiculo(veiculo)
                                                    .entrada(LocalDateTime.now()
                                                                          .minus(1, ChronoUnit.HOURS))
                                                    .status(new EmAbertoStatus())
                                                    .build());
    }

    /**
     * <p>
     * Método <b>testConstrutorSucessoComStatusAguardandoPagamento</b> responsável
     * por testar o cenário de sucesso no construtor no cenário de ticket aguardando
     * pagamento
     * </p>
     **/
    @Test
    void testConstrutorSucessoComStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                                    .estacionamento(estacionamento)
                                                    .veiculo(veiculo)
                                                    .entrada(LocalDateTime.now()
                                                                          .minus(1, ChronoUnit.HOURS))
                                                    .saida(LocalDateTime.now())
                                                    .status(new AguardandoPagamentoStatus())
                                                    .build());
    }

    /**
     * <p>
     * Método <b>testConstrutorSucessoComStatusCancelado</b> responsável por testar
     * o cenário de sucesso no construtor no cenário de ticket cancelado
     * </p>
     **/
    @Test
    void testConstrutorSucessoComStatusCancelado() {
        assertDoesNotThrow(() -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testConstrutorSucessoComStatusFinalizado</b> responsável por testar
     * o cenário de sucesso no construtor no cenário de ticket finalizado
     * </p>
     **/
    @Test
    void testConstrutorSucessoComStatusFinalizado() {
        assertDoesNotThrow(() -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testConstrutorFalhaSemIdentificador</b> responsável por testar o
     * cenário de falha no construtor não informando o identificador
     * </p>
     **/
    @Test
    void testConstrutorFalhaSemIdentificador() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().estacionamento(estacionamento)
                                         .veiculo(veiculo)
                                         .entrada(LocalDateTime.now()
                                                               .minus(1, ChronoUnit.HOURS))
                                         .saida(LocalDateTime.now())
                                         .status(new AguardandoPagamentoStatus())
                                         .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Identificador não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorFalhaSemEstacionamento</b> responsável por testar o
     * cenário de falha no construtor não informando o estacionamento
     * </p>
     **/
    @Test
    void testConstrutorFalhaSemEstacionamento() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                         .veiculo(veiculo)
                                         .entrada(LocalDateTime.now()
                                                               .minus(1, ChronoUnit.HOURS))
                                         .saida(LocalDateTime.now())
                                         .status(new AguardandoPagamentoStatus())
                                         .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Estacionamento não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorFalhaSemVeiculo</b> responsável por testar o cenário
     * de falha no construtor não informando o veiculo
     * </p>
     **/
    @Test
    void testConstrutorFalhaSemVeiculo() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                         .estacionamento(estacionamento)
                                         .entrada(LocalDateTime.now()
                                                               .minus(1, ChronoUnit.HOURS))
                                         .saida(LocalDateTime.now())
                                         .status(new AguardandoPagamentoStatus())
                                         .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Veículo não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorFalhaSemDataEntrada</b> responsável por testar o
     * cenário de falha no construtor não informando a data de entrada
     * </p>
     **/
    @Test
    void testConstrutorFalhaSemDataEntrada() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                         .estacionamento(estacionamento)
                                         .veiculo(veiculo)
                                         .saida(LocalDateTime.now())
                                         .status(new AguardandoPagamentoStatus())
                                         .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de entrada não informada.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorFalhaSemDataSaida</b> responsável por testar o
     * cenário de falha no construtor não informando a data de saída
     * </p>
     **/
    @Test
    void testConstrutorFalhaSemDataSaida() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                         .estacionamento(estacionamento)
                                         .veiculo(veiculo)
                                         .entrada(LocalDateTime.now()
                                                               .minus(1, ChronoUnit.HOURS))
                                         .status(new AguardandoPagamentoStatus())
                                         .build());

        assertTrue(exception instanceof TicketInvalidoException);
        assertEquals("Data de saída não informada.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorFalhaComDataDeEntradaPosteriorASaida</b> responsável
     * por testar o cenário de falha no construtor com data de entrada maior que a
     * data de saida
     * </p>
     **/
    @Test
    void testConstrutorFalhaComDataDeEntradaPosteriorASaida() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método
     * <b>testConstrutorFalhaTicketCancelamentoSemMotivoDeCancelamentoInformado</b>
     * responsável por testar o cenário de falha no construtor não informando o
     * motivo de cancelamento
     * </p>
     **/
    @Test
    void testConstrutorFalhaTicketCancelamentoSemMotivoDeCancelamentoInformado() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método
     * <b>testConstrutorFalhaTicketCancelamentoSemDataDeCancelamentoInformado</b>
     * responsável por testar o cenário de falha no construtor não informando a data
     * de cancelamento
     * </p>
     **/
    @Test
    void testConstrutorFalhaTicketCancelamentoSemDataDeCancelamentoInformado() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testConstrutorFalhaTicketFinalizadoSemDataDeFechamentoInformada</b>
     * responsável por testar o cenário de falha no construtor não informando a data
     * de fechamento
     * </p>
     **/
    @Test
    void testConstrutorFalhaTicketFinalizadoSemDataDeFechamentoInformada() {
        Exception exception = assertThrows(Exception.class,
                () -> new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testPagarSucessoStatusEmAberto</b> responsável por testar o cenário
     * de sucesso de alteração de status de em aberto para aguardando pagamento
     * </p>
     **/
    @Test
    void testPagarSucessoStatusEmAberto() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
                                               .estacionamento(estacionamento)
                                               .veiculo(veiculo)
                                               .entrada(LocalDateTime.now()
                                                                     .minus(1, ChronoUnit.HOURS))
                                               .status(new EmAbertoStatus())
                                               .build();

            ticket.calcularEstadia();
        });
    }

    /**
     * <p>
     * Método <b>testPagarFalhaStatusAguardandoPagamento</b> responsável por testar
     * o cenário de falha de alteração de status de aguardando pagamento para
     * aguardando pagamento
     * </p>
     **/
    @Test
    void testPagarFalhaStatusAguardandoPagamento() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testPagarFalhaStatusCancelado</b> responsável por testar o cenário
     * de falha de alteração de status de cancelado para aguardando pagamento
     * </p>
     **/
    @Test
    void testPagarFalhaStatusCancelado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testPagarFalhaStatusFinalizado</b> responsável por testar o cenário
     * de falha de alteração de status de finalizado para aguardando pagamento
     * </p>
     **/
    @Test
    void testPagarFalhaStatusFinalizado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testFinalizarSucessoStatusAguardandoPagamento</b> responsável por
     * testar o cenário de sucesso de alteração de status de aguardando pagamento
     * para finalizado
     * </p>
     **/
    @Test
    void testFinalizarSucessoStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testFinalizarFalhaStatusEmAberto</b> responsável por testar o
     * cenário de falha de alteração de status de em aberto para finalizado
     * </p>
     **/
    @Test
    void testFinalizarFalhaStatusEmAberto() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testFinalizarFalhaStatusCancelado</b> responsável por testar o
     * cenário de falha de alteração de status de cancelado para finalizado
     * </p>
     **/
    @Test
    void testFinalizarFalhaStatusCancelado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testFinalizarFalhaStatusFinalizado</b> responsável por testar o
     * cenário de falha de alteração de status de finalizado para finalizado
     * </p>
     **/
    @Test
    void testFinalizarFalhaStatusFinalizado() {
        Exception exception = assertThrows(Exception.class, () -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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

    /**
     * <p>
     * Método <b>testCancelarSucessoStatusAguardandoPagamento</b> responsável por
     * testar o cenário de sucesso de alteração de status de aguardando pagamento
     * para cancelado
     * </p>
     **/
    @Test
    void testCancelarSucessoStatusAguardandoPagamento() {
        assertDoesNotThrow(() -> {
            Ticket ticket = new TicketBuilder().identificador("00000000-0000-0000-0000-000000000000")
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
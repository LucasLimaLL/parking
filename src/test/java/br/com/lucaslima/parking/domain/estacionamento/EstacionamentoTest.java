package br.com.lucaslima.parking.domain.estacionamento;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    @Test
    void testConstrutorSucesso() {
        assertDoesNotThrow(() -> new EstacionamentoBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .nome("Estacionamento Teste")
                .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                        LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                        LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.WEDNESDAY,
                        LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.THURSDAY,
                        LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                        LocalTime.of(17, 0))
                .horarioFuncionamento(DayOfWeek.SATURDAY,
                        LocalTime.of(8, 0), LocalTime.of(13, 0))
                .quantidadeAtual(0)
                .lotacaoMaxima(50)
                .build());
    }

    /**
     * <p>
     * Método <b>testConstrutorIdentificadorNulo</b> responsável por testar o
     * cenário de falha na criação de estacionamento passando o identificador nulo
     * </p>
     **/
    @Test
    void testConstrutorIdentificadorNulo() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .nome("Estacionamento Teste")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(0)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Identificador não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorIdentificadorVazio</b> responsável por testar o
     * cenário de falha na criação de estacionamento passando o identificador vazio
     * </p>
     **/
    @Test
    void testConstrutorIdentificadorVazio() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("")
                        .nome("Estacionamento Teste")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(0)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Identificador não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorNomeNulo</b> responsável por testar o cenário de
     * falha no construtor passando o nome nulo
     * </p>
     **/
    @Test
    void testConstrutorNomeNulo() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(0)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Nome não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorNomeNulo</b> responsável por testar o cenário de
     * falha no construtor passando o nome vazio
     * </p>
     **/
    @Test
    void testConstrutorNomeVazio() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .nome("")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(0)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Nome não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorHorarioFuncionamentoNulo</b> responsável por testar o
     * cenário de falha não informando a lista de horários de funcionamento
     * </p>
     **/
    @Test
    void testConstrutorHorarioFuncionamentoNulo() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .nome("Estacionamento Teste")
                        .quantidadeAtual(0)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Horário de funcionamento não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorQuantidadeAtualMaiorQueLotacaoMaxima</b> responsável
     * por testar o cenário de falha com o estacionamento com capacidade atual maior
     * que a lotação máxima
     * </p>
     **/
    @Test
    void testConstrutorQuantidadeAtualMaiorQueLotacaoMaxima() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .nome("Estacionamento Teste")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(51)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Quantidade atual não pode ser maior que a lotação máxima.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * * Método <b>testConstrutorQuantidadeAtualIgualLotacaoMaxima</b> responsável
     * por testar o cenário de falha com o estacionamento com capacidade atual igual
     * que a lotação máxima
     * </p>
     **/
    @Test
    void testConstrutorQuantidadeAtualIgualLotacaoMaxima() {
        assertDoesNotThrow(() -> new EstacionamentoBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .nome("Estacionamento Teste")
                .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                        LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                        LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.WEDNESDAY,
                        LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.THURSDAY,
                        LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                        LocalTime.of(17, 0))
                .horarioFuncionamento(DayOfWeek.SATURDAY,
                        LocalTime.of(8, 0), LocalTime.of(13, 0))
                .quantidadeAtual(50)
                .lotacaoMaxima(50)
                .build());
    }

    /**
     * <p>
     * Método <b>testConstrutorQuantidadeAtualNulo</b> responsável por testar o
     * cenário de falha no construtor passando a quantidade atual nula
     * </p>
     **/
    @Test
    void testConstrutorQuantidadeAtualNulo() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .nome("Estacionamento Teste")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(null)
                        .lotacaoMaxima(50)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Quantidade atual não informada.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorLotacaoMaximaNaoInformado</b> responsável por testar
     * o cenário de falha no construtor passando a lotação nula
     * </p>
     **/
    @Test
    void testConstrutorLotacaoMaximaNaoInformado() {
        Exception exception = assertThrows(Exception.class,
                () -> new EstacionamentoBuilder()
                        .identificador("00000000-0000-0000-0000-000000000000")
                        .nome("Estacionamento Teste")
                        .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0),
                                LocalTime.of(18, 0))
                        .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0),
                                LocalTime.of(17, 0))
                        .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0),
                                LocalTime.of(13, 0))
                        .quantidadeAtual(0)
                        .build());

        assertTrue(exception instanceof EstacionamentoInvalidoException);
        assertEquals("Lotação máxima não informada.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testVerificarCapacidadeQuantidadeAtualMenorQueLotacaoMaxima</b>
     * responsável por testar o cenário de sucesso do método veiculoPodeSerLiberado
     * </p>
     **/
    @Test
    void testVerificarCapacidadeQuantidadeAtualMenorQueLotacaoMaxima() {
        Estacionamento estacionamento = new EstacionamentoBuilder()
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

        assertTrue(estacionamento.veiculoPodeSerLiberado());
    }

    /**
     * <p>
     * Método <b>testVerificarCapacidadeQuantidadeAtualMenorQueLotacaoMaxima</b>
     * responsável por testar o cenário de falha do método veiculoPodeSerLiberado
     * </p>
     **/
    @Test
    void testVerificarCapacidadeQuantidadeAtualIgualLotacaoMaxima() {
        Estacionamento estacionamento = new EstacionamentoBuilder()
                .identificador("00000000-0000-0000-0000-000000000000")
                .nome("Estacionamento Teste")
                .horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
                .horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(17, 0))
                .horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0), LocalTime.of(13, 0))
                .quantidadeAtual(50)
                .lotacaoMaxima(50)
                .build();

        assertFalse(estacionamento.veiculoPodeSerLiberado());
    }
}
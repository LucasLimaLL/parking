package br.com.lucaslima.parking.domain.tabelapreco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class TabelaPrecoTest {

    /**
     * <p>
     * Método <b>testConstrutorSucesso</b> responsável por testar o cenário de
     * sucesso no construtor
     * </p>
     **/
    @Test
    void testConstrutorSucesso() {
        TabelaPreco tabela = new TabelaPrecoBuilder().quinzeMinutos(BigDecimal.valueOf(2))
                                                     .umaHora(BigDecimal.valueOf(8))
                                                     .horaAdicional(BigDecimal.valueOf(5))
                                                     .diaria(BigDecimal.valueOf(20))
                                                     .build();

        assertTrue(Objects.nonNull(tabela));
        assertEquals(BigDecimal.valueOf(2), tabela.getValor15min());
        assertEquals(BigDecimal.valueOf(8), tabela.getValor1h());
        assertEquals(BigDecimal.valueOf(5), tabela.getValorHoraAdicional());
        assertEquals(BigDecimal.valueOf(20), tabela.getValorDiaria());
    }

    /**
     * <p>
     * Método <b>testConstrutorSemInformarValorDe15min</b> responsável por testar o
     * cenário de falha não informando o valor para 15 minutos
     * </p>
     **/
    @Test
    void testConstrutorSemInformarValorDe15min() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder().umaHora(BigDecimal.valueOf(8))
                                    .horaAdicional(BigDecimal.valueOf(5))
                                    .diaria(BigDecimal.valueOf(20))
                                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para quinze minutos não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorSemInformarValorDe1h</b> responsável por testar o
     * cenário de falha não informando o valor para 1 hora
     * </p>
     **/
    @Test
    void testConstrutorSemInformarValorDe1h() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder().quinzeMinutos(BigDecimal.valueOf(2))
                                    .horaAdicional(BigDecimal.valueOf(5))
                                    .diaria(BigDecimal.valueOf(20))
                                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para uma hora não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorSemInformarValorDeHoraAdicional</b> responsável por
     * testar o cenário de falha não informando o valor para hora adicional
     * </p>
     **/
    @Test
    void testConstrutorSemInformarValorDeHoraAdicional() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder().quinzeMinutos(BigDecimal.valueOf(2))
                                    .umaHora(BigDecimal.valueOf(8))
                                    .diaria(BigDecimal.valueOf(20))
                                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para hora adicional não informado.", exception.getLocalizedMessage());
    }

    /**
     * <p>
     * Método <b>testConstrutorSemInformarValorDeDiaria</b> responsável por testar o
     * cenário de falha não informando o valor para diaria
     * </p>
     **/
    @Test
    void testConstrutorSemInformarValorDeDiaria() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder().quinzeMinutos(BigDecimal.valueOf(2))
                                    .umaHora(BigDecimal.valueOf(8))
                                    .horaAdicional(BigDecimal.valueOf(5))
                                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para diária não informado.", exception.getLocalizedMessage());
    }
}
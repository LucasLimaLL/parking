package br.com.lucaslima.parking.domain.tabelapreco;

import br.com.lucaslima.parking.domain.ticket.TicketBuilder;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TabelaPrecoTest {

    @Test
    void testConstrutorSucesso() {
        TabelaPreco tabela = new TabelaPrecoBuilder()
                .quinzeMinutos(BigDecimal.valueOf(2))
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

    @Test
    void testConstrutorSemInformarValorDe15min() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder()
                    .umaHora(BigDecimal.valueOf(8))
                    .horaAdicional(BigDecimal.valueOf(5))
                    .diaria(BigDecimal.valueOf(20))
                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para quinze minutos não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorSemInformarValorDe1h() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder()
                    .quinzeMinutos(BigDecimal.valueOf(2))
                    .horaAdicional(BigDecimal.valueOf(5))
                    .diaria(BigDecimal.valueOf(20))
                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para uma hora não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorSemInformarValorDeHoraAdicional() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder()
                    .quinzeMinutos(BigDecimal.valueOf(2))
                    .umaHora(BigDecimal.valueOf(8))
                    .diaria(BigDecimal.valueOf(20))
                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para hora adicional não informado.", exception.getLocalizedMessage());
    }

    @Test
    void testConstrutorSemInformarValorDeDiaria() {
        Exception exception = assertThrows(Exception.class, () -> {
            new TabelaPrecoBuilder()
                    .quinzeMinutos(BigDecimal.valueOf(2))
                    .umaHora(BigDecimal.valueOf(8))
                    .horaAdicional(BigDecimal.valueOf(5))
                    .build();
        });

        assertTrue(exception instanceof TabelaPrecoInvalidoException);
        assertEquals("Valor para diária não informado.", exception.getLocalizedMessage());
    }
}
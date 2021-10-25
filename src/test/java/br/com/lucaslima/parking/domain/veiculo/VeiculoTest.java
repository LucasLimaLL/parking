package br.com.lucaslima.parking.domain.veiculo;

import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoInvalidoException;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    @Test
    void testSucesso() {
        assertDoesNotThrow(() -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("AAA0000")
                .marca("FIAT")
                .modelo("UNO")
                .build());
    }

    @Test
    void testPlacaNula() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .marca("FIAT")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
    }

    @Test
    void testPlacaVazia() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("")
                .marca("FIAT")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
    }

    @Test
    void testPlacaInvalida() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("!@#$%&")
                .marca("FIAT")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
    }

    @Test
    void testTipoVeiculoNulo() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .placa("AAA0000")
                .marca("FIAT")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Tipo de veículo não é válido.", exception.getLocalizedMessage());
    }

    @Test
    void testTipoVeiculoInvalido() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(-1)
                .placa("AAA0000")
                .marca("FIAT")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Tipo de veículo não é válido.", exception.getLocalizedMessage());
    }

    @Test
    void testMarcaNula() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("AAA0000")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Marca não foi informada.", exception.getLocalizedMessage());
    }

    @Test
    void testMarcaInvalida() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("AAA0000")
                .marca("")
                .modelo("UNO")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Marca não foi informada.", exception.getLocalizedMessage());
    }

    @Test
    void testModeloNulo() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("AAA0000")
                .marca("FIAT")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Modelo não foi informado.", exception.getLocalizedMessage());
    }

    @Test
    void testModeloInvalido() {
        Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
                .tipoVeiculo(TipoVeiculo.CARRO.getOrdinal())
                .placa("AAA0000")
                .marca("FIAT")
                .modelo("")
                .build());

        assertTrue(exception instanceof VeiculoInvalidoExcpetion);
        assertEquals("Modelo não foi informado.", exception.getLocalizedMessage());
    }
}
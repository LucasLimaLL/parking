package br.com.lucaslima.parking.domain.veiculo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

class VeiculoTest {

	/**
	 * <p>
	 * Método <b>testSucesso</b> responsável por testar o cenário de sucesso no
	 * construtor
	 * </p>
	 **/
	@Test
	void testSucesso() {
		assertDoesNotThrow(() -> new VeiculoBuilder().tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("AAA0000")
				.marca("FIAT").modelo("UNO").build());
	}

	/**
	 * <p>
	 * Método <b>testPlacaNula</b> responsável por testar o cenário de falha no
	 * construtor quando não informa a placa
	 * </p>
	 **/
	@Test
	void testPlacaNula() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).marca("FIAT").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testPlacaVazia</b> responsável por testar o cenário de falha no
	 * construtor quando informa uma placa vazia
	 * </p>
	 **/
	@Test
	void testPlacaVazia() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("").marca("FIAT").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testPlacaInvalida</b> responsável por testar o cenário de falha no
	 * construtor quando informa uma placa invalida
	 * </p>
	 **/
	@Test
	void testPlacaInvalida() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("!@#$%&").marca("FIAT").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Placa não foi informada ou inválida.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testTipoVeiculoNulo</b> responsável por testar o cenário de falha
	 * no construtor quando não informa o tipo de veiculo
	 * </p>
	 **/
	@Test
	void testTipoVeiculoNulo() {
		Exception exception = assertThrows(Exception.class,
				() -> new VeiculoBuilder().placa("AAA0000").marca("FIAT").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Tipo de veículo não é válido.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testTipoVeiculoInvalido</b> responsável por testar o cenário de
	 * falha no construtor quando infoma o tipo de veiculo invalido
	 * </p>
	 **/
	@Test
	void testTipoVeiculoInvalido() {
		Exception exception = assertThrows(Exception.class,
				() -> new VeiculoBuilder().tipoVeiculo(-1).placa("AAA0000").marca("FIAT").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Tipo de veículo não é válido.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testMarcaNula</b> responsável por testar o cenário de falha no
	 * construtor quando não informa a marca
	 * </p>
	 **/
	@Test
	void testMarcaNula() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("AAA0000").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Marca não foi informada.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testMarcaInvalida</b> responsável por testar o cenário de falha no
	 * construtor quando informa uma marca vazia
	 * </p>
	 **/
	@Test
	void testMarcaInvalida() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("AAA0000").marca("").modelo("UNO").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Marca não foi informada.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testModeloNulo</b> responsável por testar o cenário de falha no
	 * construtor quando não informa o modelo
	 * </p>
	 **/
	@Test
	void testModeloNulo() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("AAA0000").marca("FIAT").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Modelo não foi informado.", exception.getLocalizedMessage());
	}

	/**
	 * <p>
	 * Método <b>testModeloInvalido</b> responsável por testar o cenário de falha no
	 * construtor quando informa um modelo vazio
	 * </p>
	 **/
	@Test
	void testModeloInvalido() {
		Exception exception = assertThrows(Exception.class, () -> new VeiculoBuilder()
				.tipoVeiculo(TipoVeiculo.CARRO.getOrdinal()).placa("AAA0000").marca("FIAT").modelo("").build());

		assertTrue(exception instanceof VeiculoInvalidoExcpetion);
		assertEquals("Modelo não foi informado.", exception.getLocalizedMessage());
	}
}
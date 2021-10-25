package br.com.lucaslima.parking.domain.tabelapreco;

import java.math.BigDecimal;

/**
 * <p>
 * Classe <b>TabelaPrecoBuilder</b> responsável por criar objetos da classe
 * TabelaPreco
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class TabelaPrecoBuilder {

	private BigDecimal valor15min;
	private BigDecimal valor1h;
	private BigDecimal valorHoraAdicional;
	private BigDecimal valorDiaria;

	public TabelaPrecoBuilder quinzeMinutos(BigDecimal valor15min) {
		this.valor15min = valor15min;
		return this;
	}

	public TabelaPrecoBuilder umaHora(BigDecimal valor1h) {
		this.valor1h = valor1h;
		return this;
	}

	public TabelaPrecoBuilder horaAdicional(BigDecimal valorHoraAdicional) {
		this.valorHoraAdicional = valorHoraAdicional;
		return this;
	}

	public TabelaPrecoBuilder diaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
		return this;
	}

	public TabelaPreco build() {
		return new TabelaPreco(this.valor15min, this.valor1h, this.valorHoraAdicional, this.valorDiaria);
	}
}

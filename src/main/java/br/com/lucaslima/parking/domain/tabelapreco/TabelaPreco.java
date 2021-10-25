package br.com.lucaslima.parking.domain.tabelapreco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * Classe <b>TabelaPreco</b> responsável por representar o objeto de negócio
 * Tabela de preços
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class TabelaPreco implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal valor15min;
	private BigDecimal valor1h;
	private BigDecimal valorHoraAdicional;
	private BigDecimal valorDiaria;

	public TabelaPreco(BigDecimal valor15min, BigDecimal valor1h, BigDecimal valorHoraAdicional,
			BigDecimal valorDiaria) {

		if (Objects.isNull(valor15min)) {
			throw new TabelaPrecoInvalidoException("Valor para quinze minutos não informado.");
		}

		if (Objects.isNull(valor1h)) {
			throw new TabelaPrecoInvalidoException("Valor para uma hora não informado.");
		}

		if (Objects.isNull(valorHoraAdicional)) {
			throw new TabelaPrecoInvalidoException("Valor para hora adicional não informado.");
		}

		if (Objects.isNull(valorDiaria)) {
			throw new TabelaPrecoInvalidoException("Valor para diária não informado.");
		}

		this.valor15min = valor15min;
		this.valor1h = valor1h;
		this.valorHoraAdicional = valorHoraAdicional;
		this.valorDiaria = valorDiaria;
	}

	public BigDecimal getValor15min() {
		return valor15min;
	}

	public BigDecimal getValor1h() {
		return valor1h;
	}

	public BigDecimal getValorHoraAdicional() {
		return valorHoraAdicional;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
}

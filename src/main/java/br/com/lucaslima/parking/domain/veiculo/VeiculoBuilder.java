package br.com.lucaslima.parking.domain.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.Placa;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

/**
 * <p>
 * Classe <b>VeiculoBuilder</b> responsável por criar objetos da classe Veiculo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class VeiculoBuilder {

	private String placa;
	private Integer tipoVeiculo;
	private String marca;
	private String modelo;

	public VeiculoBuilder placa(String placa) {
		this.placa = placa;
		return this;
	}

	public VeiculoBuilder tipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
		return this;
	}

	public VeiculoBuilder marca(String marca) {
		this.marca = marca;
		return this;
	}

	public VeiculoBuilder modelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public Veiculo build() {
		return new Veiculo(new Placa(placa), TipoVeiculo.getByOridinal(tipoVeiculo), new Marca(marca),
				new Modelo(modelo));
	}
}

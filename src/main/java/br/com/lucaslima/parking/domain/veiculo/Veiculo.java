package br.com.lucaslima.parking.domain.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.Placa;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * Classe <b>Veiculo</b> responsável por representar o objeto de negócio Veiculo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Placa placa;
	private TipoVeiculo tipoVeiculo;
	private Marca marca;
	private Modelo modelo;

	public Veiculo(Placa placa, TipoVeiculo tipoVeiculo, Marca marca, Modelo modelo) {

		if (Objects.isNull(tipoVeiculo)) {
			throw new VeiculoInvalidoExcpetion("Tipo de veículo não é válido.");
		}

		this.tipoVeiculo = tipoVeiculo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
	}

	public Placa getPlaca() {
		return placa;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public Marca getMarca() {
		return marca;
	}

	public Modelo getModelo() {
		return modelo;
	}
}

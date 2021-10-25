package br.com.lucaslima.parking.domain.veiculo.vo;

import br.com.lucaslima.parking.domain.veiculo.VeiculoInvalidoExcpetion;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * <p>
 * Classe <b>Placa</b> responsável por representar a placa de um veículo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Placa {

	private String numeroPlaca;

	public Placa(String numeroPlaca) {
		if (StringUtils.isBlank(numeroPlaca) || !numeroPlaca.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")) {
			throw new VeiculoInvalidoExcpetion("Placa não foi informada ou inválida.");
		}

		this.numeroPlaca = numeroPlaca;
	}

	public String getNumeroPlaca() {
		return numeroPlaca;
	}

}

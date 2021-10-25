package br.com.lucaslima.parking.domain.veiculo.vo;

import br.com.lucaslima.parking.domain.veiculo.VeiculoInvalidoExcpetion;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * <p>
 * Classe <b>Modelo</b> responsável por representar o modelo de um veículo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Modelo {

    private String modelo;

    public Modelo(String modelo) {
        if (StringUtils.isBlank(modelo)) {
            throw new VeiculoInvalidoExcpetion("Modelo não foi informado.");
        }

        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }
}

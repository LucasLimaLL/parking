package br.com.lucaslima.parking.domain.veiculo.vo;

import br.com.lucaslima.parking.domain.veiculo.VeiculoInvalidoExcpetion;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * <p>
 * Classe <b>Marca</b> responsável por representar a marca de um veículo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Marca {

    private String marca;

    public Marca(String marca) {
        if (StringUtils.isBlank(marca)) {
            throw new VeiculoInvalidoExcpetion("Marca não foi informada.");
        }

        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }
}

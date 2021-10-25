package br.com.lucaslima.parking.usecase.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

import java.util.List;

/**
 * <p>Interface BuscaModeloRepository responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public interface BuscaModeloRepository {

    List<Modelo> buscar(Marca marca, TipoVeiculo tipoVeiculo);
}

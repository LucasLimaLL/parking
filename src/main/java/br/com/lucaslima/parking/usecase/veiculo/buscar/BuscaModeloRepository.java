package br.com.lucaslima.parking.usecase.veiculo.buscar;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

import java.util.List;

/**
 * <p>
 * Interface <b>BuscaModeloRepository</b> responsável por abstrair a camada de
 * persistencia referente ao fluxo de busca de modelos
 * </p>
 *
 * @author Lucas Lima
 * @since 30/09/2021
 **/
public interface BuscaModeloRepository {

	List<Modelo> buscar(Marca marca, TipoVeiculo tipoVeiculo);
}

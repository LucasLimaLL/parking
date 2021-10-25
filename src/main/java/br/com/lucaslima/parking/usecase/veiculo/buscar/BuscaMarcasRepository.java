package br.com.lucaslima.parking.usecase.veiculo.buscar;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

import java.util.List;

/**
 * <p>
 * Interface <b>BuscaEstacionamentoRepository</b> responsável por abstrair a
 * camada de persistencia referente ao fluxo de busca de marcas
 * </p>
 *
 * @author Lucas Lima
 * @since 30/09/2021
 **/
public interface BuscaMarcasRepository {

	List<Marca> buscar(TipoVeiculo tipoVeiculo);
}

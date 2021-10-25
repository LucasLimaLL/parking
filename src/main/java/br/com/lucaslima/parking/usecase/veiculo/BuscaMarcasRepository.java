package br.com.lucaslima.parking.usecase.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;

import java.util.List;

/**
 * <p>Interface BuscaMarcasRepository responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public interface BuscaMarcasRepository {

    List<Marca> buscar(TipoVeiculo tipoVeiculo);
}

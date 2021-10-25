package br.com.lucaslima.parking.usecase.veiculo.buscar;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Classe <b>BuscaModeloUseCase</b> responsável pelo cenário de pesquisa de
 * modelos
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class BuscaModeloUseCase {

	private BuscaModeloRepository buscaModeloRepository;

	public BuscaModeloUseCase(BuscaModeloRepository buscaModeloRepository) {
		this.buscaModeloRepository = buscaModeloRepository;
	}

	/**
	 * Método que busca os modelos com base na marca e tipo de veículo informados
	 * 
	 * @param marca       - marca dos veículos que serão listados
	 * @param tipoVeiculo - tipo do veiculo que será listado
	 * @return lista de modelos
	 */
	public List<Modelo> buscar(Marca marca, TipoVeiculo tipoVeiculo) {

		if (Objects.isNull(marca) || Objects.isNull(tipoVeiculo)) {
			throw new ParametrosInvalidosException("Marca ou Tipo de veículo não informado.");
		}

		List<Modelo> modelos = this.buscaModeloRepository.buscar(marca, tipoVeiculo);

		if (CollectionUtils.isEmpty(modelos)) {
			throw new RecursoNaoEncontradoException("Modelos não encontrados para a marca informada.");
		}

		return modelos;
	}
}

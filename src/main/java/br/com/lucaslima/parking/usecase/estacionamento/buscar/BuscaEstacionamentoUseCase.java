package br.com.lucaslima.parking.usecase.estacionamento.buscar;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;

/**
 * <p>
 * Classe <b>BuscaEstacionamentoUseCase</b> responsável pelo cenário de busca de
 * estacionamentos
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class BuscaEstacionamentoUseCase {

	private BuscaEstacionamentoRepository buscarEstacionamentoRepository;

	public BuscaEstacionamentoUseCase(BuscaEstacionamentoRepository buscarEstacionamentoRepository) {
		this.buscarEstacionamentoRepository = buscarEstacionamentoRepository;
	}

	/**
	 * Método que faz a busca de estacionamento por seu identificador
	 * 
	 * @param identificador
	 * @return Estacionamento
	 * @throws NenhumEstacionamentoEncontradoException no cenário de não encontrar
	 *                                                 nenhum estacionamento
	 */
	public Estacionamento buscar(String identificador) {
		if (StringUtils.isBlank(identificador)) {
			throw new NenhumEstacionamentoEncontradoException("Identificador não informado.");
		}

		Estacionamento estacionamento = buscarEstacionamentoRepository.buscar(identificador);

		if (Objects.isNull(estacionamento)) {
			throw new NenhumEstacionamentoEncontradoException("Nenhum estacionamento encontrado.");
		}

		return estacionamento;
	}

}

package br.com.lucaslima.parking.domain.estacionamento;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import br.com.lucaslima.parking.domain.estacionamento.vo.HorarioFuncionamento;

/**
 * <p>
 * Classe <b>Estacionamento</b> responsável por representar o objeto de negócio
 * Estacionamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Estacionamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String identificador;
	private String nome;
	private List<HorarioFuncionamento> horarioFuncionamento;
	private Integer quantidadeAtual;
	private Integer lotacaoMaxima;

	public Estacionamento(String identificador, String nome, List<HorarioFuncionamento> horarioFuncionamento,
			Integer quantidadeAtual, Integer lotacaoMaxima) {

		if (StringUtils.isBlank(identificador)) {
			throw new EstacionamentoInvalidoException("Identificador não informado.");
		}

		if (StringUtils.isBlank(nome)) {
			throw new EstacionamentoInvalidoException("Nome não informado.");
		}

		if (CollectionUtils.isEmpty(horarioFuncionamento)) {
			throw new EstacionamentoInvalidoException("Horário de funcionamento não informado.");
		}

		if (Objects.isNull(quantidadeAtual)) {
			throw new EstacionamentoInvalidoException("Quantidade atual não informada.");
		}

		if (Objects.isNull(lotacaoMaxima)) {
			throw new EstacionamentoInvalidoException("Lotação máxima não informada.");
		}

		if (quantidadeAtual > lotacaoMaxima) {
			throw new EstacionamentoInvalidoException("Quantidade atual não pode ser maior que a lotação máxima.");
		}
		this.identificador = identificador;
		this.nome = nome;
		this.horarioFuncionamento = horarioFuncionamento;
		this.quantidadeAtual = quantidadeAtual;
		this.lotacaoMaxima = lotacaoMaxima;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getNome() {
		return nome;
	}

	public List<HorarioFuncionamento> getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public Integer getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public Integer getLotacaoMaxima() {
		return lotacaoMaxima;
	}

	/**
	 * Método que valida se ainda há capacidade no estacionamento
	 * 
	 * @return boolean - se o veículo pode ser estacionado
	 */
	public boolean veiculoPodeSerLiberado() {
		if (this.quantidadeAtual >= this.lotacaoMaxima) {
			return false;
		}

		this.quantidadeAtual++;
		return true;
	}
}

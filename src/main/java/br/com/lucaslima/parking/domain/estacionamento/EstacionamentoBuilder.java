package br.com.lucaslima.parking.domain.estacionamento;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.lucaslima.parking.domain.estacionamento.vo.HorarioFuncionamento;

/**
 * <p>
 * Classe <b>EstacionamentoBuilder</b> responsável por criar objetos da classe
 * Estacionamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class EstacionamentoBuilder {

	private String identificador;
	private String nome;
	private List<HorarioFuncionamento> horarioFuncionamento = new ArrayList<>();
	private Integer quantidadeAtual;
	private Integer lotacaoMaxima;

	public EstacionamentoBuilder identificador(String identificador) {
		this.identificador = identificador;
		return this;
	}

	public EstacionamentoBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}

	public EstacionamentoBuilder horarioFuncionamento(DayOfWeek diaDaSemana, LocalTime horaInicio,
			LocalTime horaFechamento) {
		this.horarioFuncionamento.add(new HorarioFuncionamento(diaDaSemana, horaInicio, horaFechamento));
		return this;
	}

	public EstacionamentoBuilder quantidadeAtual(Integer quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
		return this;
	}

	public EstacionamentoBuilder lotacaoMaxima(Integer lotacaoMaxima) {
		this.lotacaoMaxima = lotacaoMaxima;
		return this;
	}

	public Estacionamento build() {
		return new Estacionamento(identificador, nome, horarioFuncionamento, quantidadeAtual, lotacaoMaxima);
	}
}

package br.com.lucaslima.parking.domain.estacionamento.vo;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * <p>
 * Classe <b>HorarioFuncionamento</b> responsável por representar a tabela de
 * funcionamento de um estacionamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class HorarioFuncionamento {

	private DayOfWeek diaDaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFechamento;

	public HorarioFuncionamento(DayOfWeek diaDaSemana, LocalTime horaInicio, LocalTime horaFechamento) {
		this.diaDaSemana = diaDaSemana;
		this.horaInicio = horaInicio;
		this.horaFechamento = horaFechamento;
	}

	public DayOfWeek getDiaDaSemana() {
		return diaDaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFechamento() {
		return horaFechamento;
	}
}

package br.com.lucaslima.parking.domain.ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.vo.Status;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;

/**
 * <p>
 * Classe <b>TicketBuilder</b> responsável por criar objetos da classe Ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class TicketBuilder {

	private String identificador;
	private Estacionamento estacionamento;
	private Veiculo veiculo;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private LocalDateTime dataCancelamento;
	private String motivoCancelamento;
	private LocalDateTime dataFinalizado;
	private Duration estadia;
	private BigDecimal valor;
	private Status status;

	public TicketBuilder identificador(String identificador) {
		this.identificador = identificador;
		return this;
	}

	public TicketBuilder estacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
		return this;
	}

	public TicketBuilder veiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
		return this;
	}

	public TicketBuilder entrada(LocalDateTime entrada) {
		this.entrada = entrada;
		return this;
	}

	public TicketBuilder saida(LocalDateTime saida) {
		this.saida = saida;
		return this;
	}

	public TicketBuilder dataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
		return this;
	}

	public TicketBuilder motivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
		return this;
	}

	public TicketBuilder dataFinalizado(LocalDateTime dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
		return this;
	}

	public TicketBuilder estadia(Duration estadia) {
		this.estadia = estadia;
		return this;
	}

	public TicketBuilder valor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}

	public TicketBuilder status(Status status) {
		this.status = status;
		return this;
	}

	public Ticket build() {
		return new Ticket(identificador, estacionamento, veiculo, entrada, saida, dataCancelamento, motivoCancelamento,
				dataFinalizado, estadia, valor, status);
	}
}

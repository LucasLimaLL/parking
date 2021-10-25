package br.com.lucaslima.parking.domain.ticket;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.vo.CanceladoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.EmAbertoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.FinalizadoStatus;
import br.com.lucaslima.parking.domain.ticket.vo.Status;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;

/**
 * <p>
 * Classe <b>Ticket</b> responsável por representar o objeto de negócio Ticket
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
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

	public Ticket(String identificador, Estacionamento estacionamento, Veiculo veiculo, LocalDateTime entrada,
			LocalDateTime saida, LocalDateTime dataCancelamento, String motivoCancelamento,
			LocalDateTime dataFinalizado, Duration estadia, BigDecimal valor, Status status) {

		if (StringUtils.isBlank(identificador)) {
			throw new TicketInvalidoException("Identificador não informado.");
		}

		if (Objects.isNull(estacionamento)) {
			throw new TicketInvalidoException("Estacionamento não informado.");
		}

		if (Objects.isNull(veiculo)) {
			throw new TicketInvalidoException("Veículo não informado.");
		}

		if (Objects.isNull(entrada)) {
			throw new TicketInvalidoException("Data de entrada não informada.");
		}

		if (Objects.isNull(status)) {
			throw new TicketInvalidoException("Status não informado.");
		}

		if (!(status instanceof EmAbertoStatus)) {
			if (Objects.isNull(saida)) {
				throw new TicketInvalidoException("Data de saída não informada.");
			}

			if (entrada.isAfter(saida)) {
				throw new TicketInvalidoException("Data de entrada não pode ser maior que a data de saída.");
			}

			if (status instanceof CanceladoStatus) {

				if (StringUtils.isBlank(motivoCancelamento)) {
					throw new TicketInvalidoException("Motivo do cancelamento não informado.");
				}

				if (Objects.isNull(dataCancelamento)) {
					throw new TicketInvalidoException("Data de cancelamento não informada.");
				}
			} else if (status instanceof FinalizadoStatus) {
				if (Objects.isNull(dataFinalizado)) {
					throw new TicketInvalidoException("Data de fechamento não informada.");
				}
			}

		}

		this.identificador = identificador;
		this.estacionamento = estacionamento;
		this.veiculo = veiculo;
		this.entrada = entrada;
		this.saida = saida;
		this.dataCancelamento = dataCancelamento;
		this.motivoCancelamento = motivoCancelamento;
		this.dataFinalizado = dataFinalizado;
		this.estadia = estadia;
		this.valor = valor;
		this.status = status;
	}

	public String getIdentificador() {
		return identificador;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public LocalDateTime getDataFinalizado() {
		return dataFinalizado;
	}

	public Duration getEstadia() {
		return estadia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Status getStatus() {
		return status;
	}

	public void informarSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void calcularEstadia() {
		this.status = this.status.pagar();
		this.estadia = Duration.ZERO;

		if (Objects.nonNull(this.entrada) && Objects.nonNull(this.saida)) {
			this.estadia = Duration.between(this.entrada, this.saida);
		}
	}

	public void finalizar(LocalDateTime dataFinalizado) {
		this.status = this.status.finalizar();
		this.dataFinalizado = dataFinalizado;
	}

	public void cancelar(String motivoCancelamento, LocalDateTime dataCancelamento) {
		this.status = this.status.cancelar();
		this.motivoCancelamento = motivoCancelamento;
		this.dataCancelamento = dataCancelamento;
	}
}

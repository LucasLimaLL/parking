package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;

/**
 * <p>
 * Classe <b>CalculoTempo1Hora</b> responsável pelo cenário de 1 hora até 1h59m
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class CalculoTempo1Hora extends CalculoTempo {

	private static final long TEMPO_PARA_REGRA = 1;

	public CalculoTempo1Hora(CalculoTempo proximo) {
		super(proximo);
	}

	/**
	 * Método que valida se a regra deve ser aplicada para essa classe
	 */
	@Override
	protected boolean aplicarRegra(Ticket ticket) {
		return Long.valueOf(ticket.getEstadia().toHours()) == TEMPO_PARA_REGRA;
	}

	/**
	 * Método que retorna o preço com base na tabela de preco
	 */
	@Override
	protected BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco) {
		return tabelaPreco.getValor1h();
	}
}

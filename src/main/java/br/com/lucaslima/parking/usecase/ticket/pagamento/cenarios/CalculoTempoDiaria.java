package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import java.math.BigDecimal;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

/**
 * <p>
 * Classe <b>CalculoTempo1Hora</b> responsável pelo cenário de mais de 7h
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class CalculoTempoDiaria extends CalculoTempo {

	private static final long TEMPO_PARA_REGRA = 6;

	public CalculoTempoDiaria(CalculoTempo proximo) {
		super(proximo);
	}

	/**
	 * Método que valida se a regra deve ser aplicada para essa classe
	 */
	@Override
	protected boolean aplicarRegra(Ticket ticket) {
		return Long.valueOf(ticket.getEstadia().toHours()) > TEMPO_PARA_REGRA;
	}

	/**
	 * Método que retorna o preço com base na tabela de preco
	 */
	@Override
	protected BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco) {
		if (ticket.getEstadia().toDays() == 0) {
			return tabelaPreco.getValorDiaria();
		}

		return tabelaPreco.getValorDiaria().multiply(new BigDecimal(Long.valueOf(ticket.getEstadia().toDays())));

	}
}

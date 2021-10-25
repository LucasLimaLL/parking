package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * Classe <b>CalculoTempo</b> responsável por ser o template de cálculo de todos
 * os cenários
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public abstract class CalculoTempo {

	protected CalculoTempo proximo;

	public CalculoTempo(CalculoTempo proximo) {
		this.proximo = proximo;
	}

	/**
	 * Método que faz a validação se a classe é a ideal para o tempo de estadia
	 * 
	 * @param ticket      - ticket a ser calculado o valor
	 * @param tabelaPreco - tabela de precos
	 * @return preço da estadia
	 */
	public BigDecimal validar(Ticket ticket, TabelaPreco tabelaPreco) {

		if (aplicarRegra(ticket)) {
			return calcularValor(ticket, tabelaPreco);
		}

		return Objects.nonNull(proximo) ? this.proximo.validar(ticket, tabelaPreco) : BigDecimal.ZERO;
	}

	protected abstract boolean aplicarRegra(Ticket ticket);

	protected abstract BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco);
}

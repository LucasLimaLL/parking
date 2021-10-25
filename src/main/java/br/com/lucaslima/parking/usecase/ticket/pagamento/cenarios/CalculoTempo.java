package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class CalculoTempo {

    protected CalculoTempo proximo;

    public CalculoTempo(CalculoTempo proximo) {
        this.proximo = proximo;
    }

    public BigDecimal validar(Ticket ticket, TabelaPreco tabelaPreco) {

        if (aplicarRegra(ticket)) {
            return calcularValor(ticket, tabelaPreco);
        }

        return Objects.nonNull(proximo) ? this.proximo.validar(ticket, tabelaPreco) : BigDecimal.ZERO;
    }

    protected abstract boolean aplicarRegra(Ticket ticket);

    protected abstract BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco);
}

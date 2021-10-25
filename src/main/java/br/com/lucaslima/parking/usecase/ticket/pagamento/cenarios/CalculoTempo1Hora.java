package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;

public class CalculoTempo1Hora extends CalculoTempo {

    private static final long TEMPO_PARA_REGRA = 1;

    public CalculoTempo1Hora(CalculoTempo proximo) {
        super(proximo);
    }

    @Override
    protected boolean aplicarRegra(Ticket ticket) {
        return Long.valueOf(ticket.getEstadia()
                                  .toHours()) == TEMPO_PARA_REGRA;
    }

    @Override
    protected BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco) {
        return tabelaPreco.getValor1h();
    }
}

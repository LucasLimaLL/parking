package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;

public class CalculoTempo15Minutos extends CalculoTempo {

    private static final long TEMPO_PARA_REGRA = 15;

    public CalculoTempo15Minutos(CalculoTempo proximo) {
        super(proximo);
    }

    @Override
    protected boolean aplicarRegra(Ticket ticket) {
        return Long.valueOf(ticket.getEstadia()
                                  .toMinutes()) > TEMPO_PARA_REGRA;
    }

    @Override
    protected BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco) {
        return tabelaPreco.getValor15min();
    }
}

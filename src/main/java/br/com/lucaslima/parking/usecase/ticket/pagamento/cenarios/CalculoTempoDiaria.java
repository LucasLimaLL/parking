package br.com.lucaslima.parking.usecase.ticket.pagamento.cenarios;

import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.ticket.Ticket;

import java.math.BigDecimal;

public class CalculoTempoDiaria extends CalculoTempo {

    private static final long TEMPO_PARA_REGRA = 1;

    public CalculoTempoDiaria(CalculoTempo proximo) {
        super(proximo);
    }

    @Override
    protected boolean aplicarRegra(Ticket ticket) {
        return Long.valueOf(ticket.getEstadia()
                                  .toHours()) > 6;
    }

    @Override
    protected BigDecimal calcularValor(Ticket ticket, TabelaPreco tabelaPreco) {
        if (ticket.getEstadia()
                  .toDays() == 0) {
            return tabelaPreco.getValorDiaria();
        }

        return tabelaPreco.getValorDiaria()
                          .multiply(new BigDecimal(Long.valueOf(ticket.getEstadia()
                                                                      .toDays())));

    }
}

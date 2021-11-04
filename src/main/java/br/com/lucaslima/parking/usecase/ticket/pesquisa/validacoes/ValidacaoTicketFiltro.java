package br.com.lucaslima.parking.usecase.ticket.pesquisa.validacoes;

import br.com.lucaslima.parking.usecase.ticket.validacao.Validacao;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;

/**
 * <p>Classe ValidacaoTicketFiltro responsável por</p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public abstract class ValidacaoTicketFiltro implements Validacao<TicketFiltro> {

    /**
     * Método que centraliza a validação
     */
    @Override
    public void validar(TicketFiltro ticketFiltro) {
        if (regra(ticketFiltro)) {
            throw new ParametrosInvalidosException(mensagemErro());
        }
    }

    protected abstract String mensagemErro();

    protected abstract boolean regra(TicketFiltro ticket);
}

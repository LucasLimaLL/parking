package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.validacao.ValidacaoTicket;

/**
 * <p>Classe ValidacaoOcupacaoEstacionamento responsável por</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public class ValidacaoOcupacaoEstacionamento extends ValidacaoTicket<Ticket> {

    private static final String MENSAGEM_ERRO = "Estacionamento lotado.";

    @Override
    protected String mensagemErro() {
        return MENSAGEM_ERRO;
    }

    @Override
    protected boolean regra(Ticket ticket) {
        return !ticket.getEstacionamento().verificarCapacidade();
    }
}

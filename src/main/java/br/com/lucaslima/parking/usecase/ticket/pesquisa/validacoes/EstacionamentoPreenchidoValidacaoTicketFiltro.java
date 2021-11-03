package br.com.lucaslima.parking.usecase.ticket.pesquisa.validacoes;

import java.util.Objects;

/**
 * <p>Classe <b>EstacionamentoPreenchidoValidacaoTicketFiltro</b> responsável por validar a consistência do
 * estacionamento </p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public class EstacionamentoPreenchidoValidacaoTicketFiltro extends ValidacaoTicketFiltro {

    /**
     * Método que define a mensagem de erro caso seja barrado na regra
     *
     * @return mensagem de erro
     */
    @Override
    protected String mensagemErro() {
        return "Estacionamento não informado";
    }

    /**
     * Método que faz a validação da regra
     *
     * @param ticket - filtro
     * @return boolean para se a regra vai barrar ou não
     */
    @Override
    protected boolean regra(TicketFiltro ticket) {
        return Objects.isNull(ticket.getEstacionamento());
    }
}

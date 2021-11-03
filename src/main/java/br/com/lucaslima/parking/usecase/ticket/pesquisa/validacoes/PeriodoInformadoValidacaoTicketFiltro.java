package br.com.lucaslima.parking.usecase.ticket.pesquisa.validacoes;

/**
 * <p>Classe <b>PeriodoInformadoValidacaoTicketFiltro</b> responsável por validar a consistência do
 * das datas do filtro </p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public class PeriodoInformadoValidacaoTicketFiltro extends ValidacaoTicketFiltro {

    /**
     * Método que define a mensagem de erro caso seja barrado na regra
     *
     * @return mensagem de erro
     */
    @Override
    protected String mensagemErro() {
        return null;
    }

    /**
     * Método que faz a validação da regra
     *
     * @param ticket - filtro
     * @return boolean para se a regra vai barrar ou não
     */
    @Override
    protected boolean regra(TicketFiltro ticket) {
        return false;
    }
}

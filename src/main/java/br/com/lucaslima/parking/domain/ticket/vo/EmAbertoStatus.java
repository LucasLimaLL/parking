package br.com.lucaslima.parking.domain.ticket.vo;

/**
 * <p>
 * Classe <b>EmAbertoStatus</b> responsável por representar o status de
 * Em Aberto
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class EmAbertoStatus extends Status {

    @Override
    public Status pagar() {
        return new AguardandoPagamentoStatus();
    }
}

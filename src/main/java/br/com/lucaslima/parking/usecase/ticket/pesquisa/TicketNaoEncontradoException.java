package br.com.lucaslima.parking.usecase.ticket.pesquisa;

/**
 * <p>
 * Exception <b>TicketNaoEncontradoException</b> responsável pelo cenário de
 * ticket não encontrado
 * </p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public class TicketNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public TicketNaoEncontradoException(String message) {
        super(message);
    }
}

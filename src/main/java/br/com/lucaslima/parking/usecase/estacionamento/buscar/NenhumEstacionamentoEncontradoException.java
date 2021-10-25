package br.com.lucaslima.parking.usecase.estacionamento.buscar;

/**
 * <p>Classe NenhumEstacionamentoEncontradoException responsável por</p>
 *
 * @author Lucas Lima
 * @since 30/09/2021
 **/
public class NenhumEstacionamentoEncontradoException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NenhumEstacionamentoEncontradoException(String message) {
        super(message);
    }
}

package br.com.lucaslima.parking.usecase.veiculo;

/**
 * <p>Classe RecursoNaoEncontradoException responsável por</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class RecursoNaoEncontradoException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}

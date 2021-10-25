package br.com.lucaslima.parking.usecase.ticket.validacao;

/**
 * <p>
 * Interface Validacao responsável por abstrair as validacoes a serem realizadas
 * independente do objeto a ser validado
 * </p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public interface Validacao<T> {

	void validar(T t);
}

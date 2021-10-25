package br.com.lucaslima.parking.usecase.ticket;

/**
 * <p>
 * Interface Evento responsável por abstrair todos os eventos lançados
 * </p>
 *
 * @author Lucas Lima
 * @since 25/10/2021
 **/
public interface Evento<T> {

	void executar(T t);
}

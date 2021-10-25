package br.com.lucaslima.parking.usecase.estacionamento.buscar;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;

import java.util.List;

/**
 * <p>Interface BuscaEstacionamentoRepository responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 30/09/2021
 **/
public interface BuscaEstacionamentoRepository {

    Estacionamento buscar(String identificador);

    List<Estacionamento> buscar();
}

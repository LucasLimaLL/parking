package br.com.lucaslima.parking.usecase.ticket.pagamento;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.tabelapreco.TabelaPreco;
import br.com.lucaslima.parking.domain.veiculo.Veiculo;

/**
 * <p>Interface BuscaTabelaPrecoRepository responsável por abstrair</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public interface BuscaTabelaPrecoRepository {

    TabelaPreco buscar(Estacionamento estacionamento, Veiculo veiculo);
}

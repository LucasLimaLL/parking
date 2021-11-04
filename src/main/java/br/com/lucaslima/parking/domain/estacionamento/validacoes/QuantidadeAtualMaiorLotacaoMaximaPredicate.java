package br.com.lucaslima.parking.domain.estacionamento.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;

/**
 * <p>Classe QuantidadeAtualMaiorLotacaoMaximaPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class QuantidadeAtualMaiorLotacaoMaximaPredicate extends EstacionamentoPredicate {
    @Override
    public String getMensagem() {
        return "Quantidade atual não pode ser maior que a lotação máxima.";

    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param estacionamento the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Estacionamento estacionamento) {
        return estacionamento.getQuantidadeAtual() > estacionamento.getLotacaoMaxima();
    }
}

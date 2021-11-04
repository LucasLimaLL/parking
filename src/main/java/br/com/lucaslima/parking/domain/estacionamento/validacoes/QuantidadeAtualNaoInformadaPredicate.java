package br.com.lucaslima.parking.domain.estacionamento.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoInvalidoException;

import java.util.Objects;

/**
 * <p>Classe QuantidadeAtualNaoInformadaPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class QuantidadeAtualNaoInformadaPredicate extends EstacionamentoPredicate {
    @Override
    public String getMensagem() {
        return "Quantidade atual não informada.";
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
        return Objects.isNull(estacionamento.getQuantidadeAtual());
    }
}

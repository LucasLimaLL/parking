package br.com.lucaslima.parking.domain.estacionamento.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Classe IdentificadorNaoInformadoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class IdentificadorNaoInformadoPredicate extends EstacionamentoPredicate {
    @Override
    public String getMensagem() {
        return "Identificador não informado.";
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
        return StringUtils.isBlank(estacionamento.getIdentificador());
    }
}

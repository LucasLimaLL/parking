package br.com.lucaslima.parking.domain.estacionamento.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoInvalidoException;
import org.springframework.util.CollectionUtils;

/**
 * <p>Classe HorarioFuncionamentoNaoInformadoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public class HorarioFuncionamentoNaoInformadoPredicate extends EstacionamentoPredicate {
    @Override
    public String getMensagem() {
        return "Horário de funcionamento não informado.";
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
        return CollectionUtils.isEmpty(estacionamento.getHorarioFuncionamento());
    }

}

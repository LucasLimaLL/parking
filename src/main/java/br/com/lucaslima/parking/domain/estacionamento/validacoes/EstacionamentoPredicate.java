package br.com.lucaslima.parking.domain.estacionamento.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;

import java.util.function.Predicate;

/**
 * <p>Classe EstacionamentoPredicate responsável por</p>
 *
 * @author Lucas Lima
 * @since 04/11/2021
 **/
public abstract class EstacionamentoPredicate implements Predicate<Estacionamento> {

    public abstract String getMensagem();
}

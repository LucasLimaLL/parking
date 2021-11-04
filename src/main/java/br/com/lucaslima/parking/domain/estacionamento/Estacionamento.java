package br.com.lucaslima.parking.domain.estacionamento;

import br.com.lucaslima.parking.domain.estacionamento.validacoes.EstacionamentoPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.HorarioFuncionamentoNaoInformadoPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.IdentificadorNaoInformadoPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.LotacaoMaximaNaoInformadaPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.NomeNaoInformadoPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.QuantidadeAtualMaiorLotacaoMaximaPredicate;
import br.com.lucaslima.parking.domain.estacionamento.validacoes.QuantidadeAtualNaoInformadaPredicate;
import br.com.lucaslima.parking.domain.estacionamento.vo.HorarioFuncionamento;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Classe <b>Estacionamento</b> responsável por representar o objeto de negócio
 * Estacionamento
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public class Estacionamento implements Serializable {

    private final static List<EstacionamentoPredicate> VALIDACOES;

    static {
        VALIDACOES = List.of(
                new IdentificadorNaoInformadoPredicate(),
                new NomeNaoInformadoPredicate(),
                new HorarioFuncionamentoNaoInformadoPredicate(),
                new QuantidadeAtualNaoInformadaPredicate(),
                new LotacaoMaximaNaoInformadaPredicate(),
                new QuantidadeAtualMaiorLotacaoMaximaPredicate()
                            );
    }
    private static final long serialVersionUID = 1L;
    private String identificador;
    private String nome;
    private List<HorarioFuncionamento> horarioFuncionamento;
    private Integer quantidadeAtual;
    private Integer lotacaoMaxima;

    public Estacionamento(String identificador, String nome, List<HorarioFuncionamento> horarioFuncionamento,
                          Integer quantidadeAtual, Integer lotacaoMaxima) {

        this.identificador = identificador;
        this.nome = nome;
        this.horarioFuncionamento = horarioFuncionamento;
        this.quantidadeAtual = quantidadeAtual;
        this.lotacaoMaxima = lotacaoMaxima;

        VALIDACOES.forEach(validacao -> {
            if (validacao.test(this)) {
                throw new EstacionamentoInvalidoException(validacao.getMensagem());
            }
        });
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public List<HorarioFuncionamento> getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public Integer getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    /**
     * Método que valida se ainda há capacidade no estacionamento
     *
     * @return boolean - se o veículo pode ser estacionado
     */
    public boolean veiculoPodeSerLiberado() {
        if (this.quantidadeAtual >= this.lotacaoMaxima) {
            return false;
        }

        this.quantidadeAtual++;
        return true;
    }
}

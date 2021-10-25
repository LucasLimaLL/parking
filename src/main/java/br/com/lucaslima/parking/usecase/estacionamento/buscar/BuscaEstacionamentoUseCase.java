package br.com.lucaslima.parking.usecase.estacionamento.buscar;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>Classe BuscaEstacionamentoUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 30/09/2021
 **/
public class BuscaEstacionamentoUseCase {

    private BuscaEstacionamentoRepository buscarEstacionamentoRepository;

    public BuscaEstacionamentoUseCase(
            BuscaEstacionamentoRepository buscarEstacionamentoRepository) {
        this.buscarEstacionamentoRepository = buscarEstacionamentoRepository;
    }

    public Estacionamento buscar(String identificador) {
        if (StringUtils.isEmpty(identificador)) {
            throw new NenhumEstacionamentoEncontradoException("Identificador não informado.");
        }

        Estacionamento estacionamento = buscarEstacionamentoRepository.buscar(identificador);

        if (Objects.isNull(estacionamento)) {
            throw new NenhumEstacionamentoEncontradoException("Nenhum estacionamento encontrado.");
        }

        return estacionamento;
    }

    public List<Estacionamento> buscar() {
        List<Estacionamento> estacionamentos = buscarEstacionamentoRepository.buscar();

        if (CollectionUtils.isEmpty(estacionamentos)) {
            throw new NenhumEstacionamentoEncontradoException("Nenhum estacionamento encontrado.");
        }

        return estacionamentos;
    }
}

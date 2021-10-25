package br.com.lucaslima.parking.usecase.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>Classe BuscaMarcasUseCase responsável por</p>
 *
 * @author Lucas Lima
 * @since 18/10/2021
 **/
public class BuscaMarcasUseCase {

    private BuscaMarcasRepository buscaMarcasRepository;

    public BuscaMarcasUseCase(BuscaMarcasRepository buscaMarcasRepository) {
        this.buscaMarcasRepository = buscaMarcasRepository;
    }

    public List<Marca> buscar(TipoVeiculo tipoVeiculo) {

        if (Objects.isNull(tipoVeiculo)) {
            throw new ParametrosInvalidosException("Tipo de veículo não informado.");
        }

        List<Marca> marcas = buscaMarcasRepository.buscar(tipoVeiculo);

        if (CollectionUtils.isEmpty(marcas)) {
            throw new RecursoNaoEncontradoException("Marcas não encontradas para o tipo de veículo especificado.");
        }

        return marcas;
    }
}

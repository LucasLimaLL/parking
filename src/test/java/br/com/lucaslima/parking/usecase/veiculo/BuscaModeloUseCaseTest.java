package br.com.lucaslima.parking.usecase.veiculo;

import br.com.lucaslima.parking.domain.veiculo.vo.Marca;
import br.com.lucaslima.parking.domain.veiculo.vo.Modelo;
import br.com.lucaslima.parking.domain.veiculo.vo.TipoVeiculo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaModeloUseCaseTest {

    @Mock
    private BuscaModeloRepository buscaModeloRepository;

    private BuscaModeloUseCase buscaModeloUseCase;

    @BeforeEach
    void setUp() {
        this.buscaModeloUseCase = new BuscaModeloUseCase(buscaModeloRepository);
    }

    @Test
    void testBuscarListaPreenchida() {
        when(buscaModeloRepository.buscar(Mockito.any(Marca.class), Mockito.any(TipoVeiculo.class))).thenReturn(
                List.of(new Modelo("Onix"), new Modelo("Spin"), new Modelo("Tracker")));

        assertDoesNotThrow(() -> {
            List<Modelo> modelos = this.buscaModeloUseCase.buscar(new Marca("CHEVROLET"), TipoVeiculo.CARRO);

            int i = 3;
            assertTrue(CollectionUtils.isNotEmpty(modelos));
            assertTrue(modelos.size() == i);
            assertEquals("Tracker", modelos.get(--i)
                                           .getModelo());
            assertEquals("Spin", modelos.get(--i)
                                        .getModelo());
            assertEquals("Onix", modelos.get(--i)
                                        .getModelo());
        });
    }

    @Test
    void testBuscarListaVazia() {
        when(buscaModeloRepository.buscar(Mockito.any(Marca.class), Mockito.any(TipoVeiculo.class))).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(Exception.class, () -> {
            this.buscaModeloUseCase.buscar(new Marca("CHEVROLET"), TipoVeiculo.CARRO);
        });

        assertTrue(exception instanceof RecursoNaoEncontradoException);
        assertEquals("Modelos não encontrados para a marca informada.", exception.getLocalizedMessage());
    }

    @Test
    void testBuscarParametroNulo() {

        Exception exception = assertThrows(Exception.class, () -> {
            this.buscaModeloUseCase.buscar(null, null);
        });

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Marca ou Tipo de veículo não informado.", exception.getLocalizedMessage());
    }
}
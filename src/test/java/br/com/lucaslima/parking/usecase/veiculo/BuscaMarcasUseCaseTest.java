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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaMarcasUseCaseTest {

    @Mock
    private BuscaMarcasRepository buscaMarcasRepository;

    private BuscaMarcasUseCase buscaMarcasUseCase;

    @BeforeEach
    void setUp() {
        this.buscaMarcasUseCase = new BuscaMarcasUseCase(buscaMarcasRepository);
    }

    @Test
    void testBuscarListaPreenchida() {
        when(buscaMarcasRepository.buscar(Mockito.any(TipoVeiculo.class))).thenReturn(List.of(new Marca("CHEVROLET"),
                new Marca("VOLKSWAGEN"),
                new Marca("FIAT")));


        assertDoesNotThrow(() -> {
            List<Marca> marcas = this.buscaMarcasUseCase.buscar(TipoVeiculo.CARRO);

            int i = 3;
            assertTrue(CollectionUtils.isNotEmpty(marcas));
            assertTrue(marcas.size() == i);
            assertEquals("FIAT", marcas.get(--i)
                                       .getMarca());
            assertEquals("VOLKSWAGEN", marcas.get(--i)
                                             .getMarca());
            assertEquals("CHEVROLET", marcas.get(--i)
                                            .getMarca());
        });

    }

    @Test
    void testBuscarListaVazia() {
        when(buscaMarcasRepository.buscar(Mockito.any(TipoVeiculo.class))).thenReturn(Collections.emptyList());


        Exception exception = assertThrows(Exception.class, () -> {
            this.buscaMarcasUseCase.buscar(TipoVeiculo.CARRO);
        });

        assertTrue(exception instanceof RecursoNaoEncontradoException);
        assertEquals("Marcas não encontradas para o tipo de veículo especificado.", exception.getLocalizedMessage());
    }

    @Test
    void testBuscarParametroNulo() {

        Exception exception = assertThrows(Exception.class, () -> {
            this.buscaMarcasUseCase.buscar(null);
        });

        assertTrue(exception instanceof ParametrosInvalidosException);
        assertEquals("Tipo de veículo não informado.", exception.getLocalizedMessage());
    }
}
package br.com.lucaslima.parking.usecase.estacionamento.buscar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.estacionamento.EstacionamentoBuilder;

@ExtendWith(MockitoExtension.class)
class BuscaEstacionamentoUseCaseTest {

	@Mock
	private BuscaEstacionamentoRepository buscaEstacionamentoRepository;

	private BuscaEstacionamentoUseCase buscaEstacionamentoUseCase = new BuscaEstacionamentoUseCase(
			buscaEstacionamentoRepository);

	@BeforeEach
	void setUp() {
		buscaEstacionamentoUseCase = new BuscaEstacionamentoUseCase(buscaEstacionamentoRepository);
	}

	@Test
	void testBuscarPorIdentificadorSucesso() {

		when(buscaEstacionamentoRepository.buscar(Mockito.any(String.class))).thenReturn(new EstacionamentoBuilder()
				.identificador("00000000-0000-0000-0000-000000000000").nome("Estacionamento A")
				.horarioFuncionamento(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
				.horarioFuncionamento(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
				.horarioFuncionamento(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
				.horarioFuncionamento(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(18, 0))
				.horarioFuncionamento(DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(17, 0))
				.horarioFuncionamento(DayOfWeek.SATURDAY, LocalTime.of(8, 0), LocalTime.of(13, 0)).quantidadeAtual(0)
				.lotacaoMaxima(50).build());

		Estacionamento estacionamento = buscaEstacionamentoUseCase.buscar(UUID.randomUUID().toString());

		assertTrue(Objects.nonNull(estacionamento));
		assertEquals("00000000-0000-0000-0000-000000000000", estacionamento.getIdentificador());
		assertEquals("Estacionamento A", estacionamento.getNome());
		assertFalse(CollectionUtils.isEmpty(estacionamento.getHorarioFuncionamento()));
		assertTrue(estacionamento.getHorarioFuncionamento().size() == 6);
		assertEquals(0, estacionamento.getQuantidadeAtual());
		assertEquals(50, estacionamento.getLotacaoMaxima());
	}

	@Test
	void testBuscarPorIdentificadorNaoEncontrado() {

		Exception exception = assertThrows(Exception.class, () -> {
			when(buscaEstacionamentoRepository.buscar(Mockito.any(String.class))).thenReturn(null);
			buscaEstacionamentoUseCase.buscar(UUID.randomUUID().toString());
		});

		assertTrue(exception instanceof NenhumEstacionamentoEncontradoException);
		assertEquals("Nenhum estacionamento encontrado.", exception.getLocalizedMessage());
	}

}
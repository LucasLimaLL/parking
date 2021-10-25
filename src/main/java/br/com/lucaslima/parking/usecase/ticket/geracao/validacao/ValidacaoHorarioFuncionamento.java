package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import br.com.lucaslima.parking.domain.estacionamento.vo.HorarioFuncionamento;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.validacao.ValidacaoTicket;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 * <p>Classe ValidacaoHorarioFuncionamento responsável por</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public class ValidacaoHorarioFuncionamento extends ValidacaoTicket<Ticket> {

    private static final String MENSAGEM_ERRO = "Entrada fora do horário de funcionamento.";

    @Override
    protected boolean regra(Ticket ticket) {
        Optional<HorarioFuncionamento> horarioFuncionamento = ticket.getEstacionamento()
                                                                    .getHorarioFuncionamento()
                                                                    .stream()
                                                                    .filter(horario -> horario.getDiaDaSemana()
                                                                                              .equals(LocalDateTime.now()
                                                                                                                   .getDayOfWeek()))
                                                                    .findFirst();

        LocalTime horarioChegada = ticket.getEntrada()
                                         .toLocalTime();

        return horarioChegada.isAfter(horarioFuncionamento.get()
                                                          .getHoraFechamento())
               ||
               horarioChegada.isBefore(horarioFuncionamento.get()
                                                           .getHoraInicio());
    }

    @Override
    protected String mensagemErro() {
        return MENSAGEM_ERRO;
    }
}

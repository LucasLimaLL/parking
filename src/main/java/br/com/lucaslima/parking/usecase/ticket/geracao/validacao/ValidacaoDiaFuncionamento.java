package br.com.lucaslima.parking.usecase.ticket.geracao.validacao;

import br.com.lucaslima.parking.domain.estacionamento.vo.HorarioFuncionamento;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.usecase.ticket.validacao.ValidacaoTicket;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>Classe ValidacaoDiaFuncionamento responsável por</p>
 *
 * @author Lucas Lima
 * @since 08/10/2021
 **/
public class ValidacaoDiaFuncionamento extends ValidacaoTicket<Ticket> {

    private static final String MENSAGEM_ERRO = "Entrada foi informado em dia de não funcionamento.";

    @Override
    protected boolean regra(Ticket ticket) {
        Optional<HorarioFuncionamento> horarioFuncionamento = ticket.getEstacionamento()
                                                                    .getHorarioFuncionamento()
                                                                    .stream()
                                                                    .filter(horario -> horario.getDiaDaSemana()
                                                                                              .equals(LocalDateTime.now()
                                                                                                                   .getDayOfWeek()))
                                                                    .findFirst();


        return horarioFuncionamento.isEmpty();
    }

    @Override
    protected String mensagemErro() {
        return MENSAGEM_ERRO;
    }
}

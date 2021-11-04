package br.com.lucaslima.parking.usecase.ticket.pesquisa;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.Ticket;
import br.com.lucaslima.parking.domain.ticket.vo.Status;
import br.com.lucaslima.parking.usecase.ticket.pesquisa.validacoes.TicketFiltro;
import br.com.lucaslima.parking.usecase.ticket.validacao.Validacao;
import br.com.lucaslima.parking.usecase.veiculo.buscar.ParametrosInvalidosException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Classe <b>PesquisaTicketUseCase</b> responsável pelo cenário de pesquisa
 * de tickets com base nos parametros
 * </p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public class PesquisaTicketUseCase {

    private final List<Validacao<TicketFiltro>> validacoes;
    private final PesquisaTicketRepository pesquisaTicketRepository;

    public PesquisaTicketUseCase(List<Validacao<TicketFiltro>> validacoes, PesquisaTicketRepository pesquisaTicketRepository) {
        this.validacoes = validacoes;
        this.pesquisaTicketRepository = pesquisaTicketRepository;
    }

    /**
     * Método que faz a busca no repositorio dos tickets
     *
     * @param estacionamento - estacionamento do ticket
     * @param periodoInicio  - inicio do range de data
     * @param periodoFim     - fim do range de data
     * @param status         - status dos tickets
     * @return lista de tickets com os parametros passados
     **/
    public List<Ticket> buscar(Estacionamento estacionamento, LocalDateTime periodoInicio, LocalDateTime periodoFim,
                               List<Status> status) {

        TicketFiltro ticketFiltro = new TicketFiltro(estacionamento, periodoInicio, periodoFim, status);
        validacoes.forEach(validacao -> validacao.validar(ticketFiltro));

        List<Ticket> tickets = this.pesquisaTicketRepository.buscar(estacionamento, periodoInicio, periodoFim, status);

        if (CollectionUtils.isEmpty(tickets)) {
            throw new TicketNaoEncontradoException("Tickets não encontrados com os parametros informados.");
        }

        return tickets;
    }

    /**
     * Método que faz a busca no repositorio do ticket por seu identificador
     *
     * @param identificador - identitificador do ticket
     * @return ticket
     **/
    public Ticket buscar(String identificador) {
        if (StringUtils.isBlank(identificador)) {
            throw new ParametrosInvalidosException("Identificador não informado.");
        }

        Ticket ticket = this.pesquisaTicketRepository.buscar(identificador);

        if (Objects.isNull(ticket)) {
            throw new TicketNaoEncontradoException("Tickets não encontrados com os parametros informados.");
        }

        return ticket;
    }
}

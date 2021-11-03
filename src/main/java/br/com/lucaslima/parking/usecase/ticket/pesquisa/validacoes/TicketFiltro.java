package br.com.lucaslima.parking.usecase.ticket.pesquisa.validacoes;

import br.com.lucaslima.parking.domain.estacionamento.Estacionamento;
import br.com.lucaslima.parking.domain.ticket.vo.Status;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Classe TicketFiltro responsável por representar o filtro de pesquisa</p>
 *
 * @author Lucas Lima
 * @since 26/10/2021
 **/
public class TicketFiltro implements Serializable {

    private Estacionamento estacionamento;
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFim;
    private List<Status> status;

    public TicketFiltro(Estacionamento estacionamento, LocalDateTime periodoInicio, LocalDateTime periodoFim,
                        List<Status> status) {
        this.estacionamento = estacionamento;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.status = status;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public LocalDateTime getPeriodoInicio() {
        return periodoInicio;
    }

    public LocalDateTime getPeriodoFim() {
        return periodoFim;
    }

    public List<Status> getStatus() {
        return status;
    }
}

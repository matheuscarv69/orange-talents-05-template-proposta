package proposta.core.feignClients.accounts.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import proposta.entities.card.entities.DueDate;

import java.time.LocalDateTime;

public class DueDateRes {

    private String id;
    private Integer dia;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataDeCriacao;

    public DueDateRes(String id,
                      Integer dia,
                      LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public DueDate toModel() {
        return new DueDate(id, dia, dataDeCriacao);
    }

}

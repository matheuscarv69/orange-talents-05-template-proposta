package proposta.core.feignClients.accounts.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import proposta.entities.card.entities.Card;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardRes {

    private String id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime emitidoEm;
    private String titular;
    private String limite;
    private DueDateRes vencimento;

    public CardRes(String id,
                   LocalDateTime emitidoEm,
                   String titular,
                   String limite,
                   DueDateRes vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
    }

    public Card toModel() {
        return new Card(id, emitidoEm, titular, new BigDecimal(limite), vencimento.toModel());
    }
}

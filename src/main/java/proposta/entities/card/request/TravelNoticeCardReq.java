package proposta.entities.card.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.TravelNoticeCard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNoticeCardReq {

    @NotBlank
    private String destinoViagem;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fimViagem;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TravelNoticeCardReq(String destinoViagem,
                               LocalDate fimViagem) {
        this.destinoViagem = destinoViagem;
        this.fimViagem = fimViagem;
    }

    public TravelNoticeCard toModel(Card card, String clientIp, String userAgent) {
        return new TravelNoticeCard(destinoViagem, fimViagem, card, clientIp, userAgent);
    }
}

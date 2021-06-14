package proposta.entities.card.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import proposta.core.feignClients.accounts.request.NotifyTravelNoticeReq;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.TravelNotice;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNoticeReq {

    @NotBlank
    private String destinoViagem;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fimViagem;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TravelNoticeReq(String destinoViagem,
                           LocalDate fimViagem) {
        this.destinoViagem = destinoViagem;
        this.fimViagem = fimViagem;
    }

    public TravelNotice toModel(Card card, String clientIp, String userAgent) {
        return new TravelNotice(destinoViagem, fimViagem, card, clientIp, userAgent);
    }

    public NotifyTravelNoticeReq toNotify() {
        return new NotifyTravelNoticeReq(destinoViagem, fimViagem);
    }


}

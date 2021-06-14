package proposta.core.feignClients.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class NotifyTravelNoticeReq {

    private String destino;
    private LocalDate validoAte;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NotifyTravelNoticeReq(String destino,
                                 LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}

package proposta.core.feignClients.accounts.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import proposta.core.feignClients.accounts.response.enums.StatusTravelNotice;

public class NotifyTravelNoticeRes {

    private StatusTravelNotice resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NotifyTravelNoticeRes(StatusTravelNotice resultado) {
        this.resultado = resultado;
    }

    public StatusTravelNotice getResultado() {
        return resultado;
    }
}

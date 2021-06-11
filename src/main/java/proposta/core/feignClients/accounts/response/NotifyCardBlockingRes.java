package proposta.core.feignClients.accounts.response;

import com.fasterxml.jackson.annotation.JsonCreator;

public class NotifyCardBlockingRes {

    private StatusBlockCardRes resultado;

    // Anotacao usada para o jackson conseguir deserializar a classe, pois ela possui so uma propriedade
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NotifyCardBlockingRes(StatusBlockCardRes resultado) {
        this.resultado = resultado;
    }

    public StatusBlockCardRes getStatusBlockCardRes() {
        return resultado;
    }
}

package proposta.core.feignClients.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResponsibleSystemReq {

    private String sistemaResponsavel;

    // Anotacao usada para o jackson conseguir deserializar a classe, pois ela possui so uma propriedade
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResponsibleSystemReq(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}

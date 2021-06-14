package proposta.entities.biometry.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import proposta.configs.validation.isBase64.IsBase64;
import proposta.entities.biometry.entities.Biometry;
import proposta.entities.card.entities.Card;

import javax.validation.constraints.NotBlank;

public class BiometryReq {

    @NotBlank
    @IsBase64
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometryReq(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometry toModel(Card card) {
        return new Biometry(this.fingerprint, card);
    }
}

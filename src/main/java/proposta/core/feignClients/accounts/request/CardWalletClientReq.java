package proposta.core.feignClients.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardWalletClientReq {

    private String email;
    private String carteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardWalletClientReq(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

}

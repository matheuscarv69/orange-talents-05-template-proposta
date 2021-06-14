package proposta.entities.card.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import proposta.core.feignClients.accounts.request.CardWalletClientReq;
import proposta.entities.card.entities.CardWallet;
import proposta.entities.card.entities.enums.Wallets;

public class CardWalletReq {

    private String email;
    private Wallets wallet;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardWalletReq(String email,
                         Wallets wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public CardWallet toModel() {
        return new CardWallet(email, wallet);
    }

    public CardWalletClientReq toCardWalletClientReq() {
        return new CardWalletClientReq(email, wallet.toString());
    }

}

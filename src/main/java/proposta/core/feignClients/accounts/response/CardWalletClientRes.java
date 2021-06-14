package proposta.core.feignClients.accounts.response;

import proposta.core.feignClients.accounts.response.enums.StatusCardWalletClient;

public class CardWalletClientRes {

    private String id;
    private StatusCardWalletClient resultado;

    public CardWalletClientRes(String id,
                               StatusCardWalletClient resultado) {
        this.id = id;
        this.resultado = resultado;
    }
}

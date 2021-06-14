package proposta.entities.card.events.eventForCardWallet;

import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.CardWallet;
import proposta.entities.card.request.CardWalletReq;

public interface EventsForCardWallet {

    CardWallet associateCardWallet(Card card, CardWalletReq cardWallet);

}

package proposta.entities.card.events.eventForCardWallet;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.core.feignClients.accounts.client.AccountsClient;
import proposta.core.feignClients.accounts.response.CardWalletClientRes;
import proposta.entities.card.controllers.AssociateCardWalletController;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.CardWallet;
import proposta.entities.card.repositories.CardRepository;
import proposta.entities.card.repositories.CardWalletRepository;
import proposta.entities.card.request.CardWalletReq;

@Primary
@Component
public class EventCardWallet implements EventsForCardWallet {

    private final Logger logger = LoggerFactory.getLogger(AssociateCardWalletController.class);

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public CardWalletRepository cardWalletRepository;

    @Autowired
    public AccountsClient accountsClient;

    @Override
    public CardWallet associateCardWallet(Card card, CardWalletReq cardWalletReq) {

        CardWallet cardWallet = cardWalletReq.toModel();

        try {
            CardWalletClientRes cardWalletRes = accountsClient.associateCardWallet(card.getId(),
                    cardWalletReq.toCardWalletClientReq());

            if (card.walletExists(cardWallet)) {
                throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "A Carteira já está associado ao cartão informado");
            }

            card.addAssociateCardWallet(cardWallet);
            cardWalletRepository.save(cardWallet);
            cardRepository.save(card);

        } catch (FeignException exception) {
            logger.error("Deu ruim na associação de cartão com carteira");
            exception.printStackTrace();
        }

        return cardWallet;
    }


}

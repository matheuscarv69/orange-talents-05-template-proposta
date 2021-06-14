package proposta.entities.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.card.entities.CardWallet;
import proposta.entities.card.entities.Card;
import proposta.entities.card.events.eventForCardWallet.EventCardWallet;
import proposta.entities.card.repositories.CardRepository;
import proposta.entities.card.request.CardWalletReq;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class AssociateCardWalletController {

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public EventCardWallet eventCardWallet;

    @PostMapping("/{idCard}/wallet")
    public ResponseEntity<?> associateCardWallet(@PathVariable String idCard,
                                                 @RequestBody @Valid CardWalletReq cardWalletReq,
                                                 UriComponentsBuilder uriBuilder) {
        Card card = getPossibleCard(idCard);

        CardWallet cardWallet = eventCardWallet.associateCardWallet(card, cardWalletReq);

        URI uri = uriBuilder.path("/api/card/wallets/{idWallet}").buildAndExpand(cardWallet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private Card getPossibleCard(String idCard) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "O Cartão informado não existe");
        }

        return possibleCard.get();
    }


}

package proposta.entities.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.card.entities.Card;
import proposta.entities.card.events.eventForBlockingCard.EventsForBlockCard;
import proposta.entities.card.repositories.CardRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class BlockCardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EventsForBlockCard eventsForBlockCard;

    @PutMapping("/{idCard}/block")
    public ResponseEntity<?> blockCard(@PathVariable String idCard) {

        Card card = getPossibleCard(idCard);

        eventsForBlockCard.sendNotificationCardBlocking(idCard, card);

        return ResponseEntity.ok().build();
    }

    /**
     * Verifica se o cartao existe e o retorna em caso de existencia
     */
    private Card getPossibleCard(String idCard) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "O Cartão informado não existe");
        }

        if (possibleCard.get().isBlock()) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
        }

        return possibleCard.get();
    }

}

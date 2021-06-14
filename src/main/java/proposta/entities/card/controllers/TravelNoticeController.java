package proposta.entities.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.card.entities.Card;
import proposta.entities.card.events.eventForTravelNotice.EventsForTravelNotice;
import proposta.entities.card.repositories.CardRepository;
import proposta.entities.card.request.TravelNoticeReq;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class TravelNoticeController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EventsForTravelNotice eventsForTravelNotice;

    @PostMapping("/{idCard}/travel")
    public ResponseEntity<?> travelNoticeCard(@PathVariable String idCard,
                                              @RequestBody @Valid TravelNoticeReq travelNoticeCardReq) {
        Card card = getPossibleCard(idCard);

        eventsForTravelNotice.sendNotificationTravelNotice(card, travelNoticeCardReq);

        return ResponseEntity.ok().build();
    }

    private Card getPossibleCard(String idCard) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }

        return possibleCard.get();
    }


}

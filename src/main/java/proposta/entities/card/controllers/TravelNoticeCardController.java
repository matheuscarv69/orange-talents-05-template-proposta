package proposta.entities.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.TravelNoticeCard;
import proposta.entities.card.repositories.CardRepository;
import proposta.entities.card.repositories.TravelNoticeCardRepository;
import proposta.entities.card.request.TravelNoticeCardReq;
import proposta.utils.GetDatasRequest;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class TravelNoticeCardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TravelNoticeCardRepository travelNoticeCardRepository;

    @Autowired
    private GetDatasRequest dataRequest;

    @PostMapping("/{idCard}/travel")
    public ResponseEntity<?> travelNoticeCard(@PathVariable String idCard,
                                              @RequestBody @Valid TravelNoticeCardReq travelNoticeCardReq) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }

        String clientIp = dataRequest.getClientIp();
        String userAgent = dataRequest.getUserAgent();

        TravelNoticeCard travelNoticeCard = travelNoticeCardReq.toModel(possibleCard.get(), clientIp, userAgent);
        travelNoticeCardRepository.save(travelNoticeCard);

        return ResponseEntity.ok().build();
    }


}

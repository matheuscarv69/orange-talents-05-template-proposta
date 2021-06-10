package proposta.entities.biometry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.biometry.repositories.BiometryRepository;
import proposta.entities.biometry.requests.BiometryReq;
import proposta.entities.card.entities.Card;
import proposta.entities.card.repositories.CardRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/biometry")
public class CreateBiometry {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BiometryRepository biometryRepository;

    @PostMapping("/{idCard}")
    public ResponseEntity<?> createBiometry(@PathVariable String idCard,
                                            @Valid @RequestBody BiometryReq biometryReq,
                                            UriComponentsBuilder uriBuilder) {
        Card card = verifyPossibleCard(idCard);

        Biometry biometry = biometryReq.toModel(card);
        card.addBiometry(biometry);

        biometryRepository.save(biometry);
        cardRepository.save(card);

        URI uri = uriBuilder.path("/api/biometry/{idBiometry}").buildAndExpand(biometry.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    private Card verifyPossibleCard(String idCard) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "O Cartão informado não existe");
        }

        return possibleCard.get();
    }

}

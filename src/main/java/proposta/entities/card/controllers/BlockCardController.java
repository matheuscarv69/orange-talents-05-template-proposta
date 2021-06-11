package proposta.entities.card.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.card.entities.BlockCard;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.StatusBlock;
import proposta.entities.card.repositories.CardRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class BlockCardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private HttpServletRequest servletRequest;

    @PutMapping("/{idCard}/block")
    public ResponseEntity<?> blockCard(@PathVariable String idCard) {

        Card card = checkPossibleCard(idCard);
        if (card.isBlock()) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
        }

        String clientIp = getClientIp();
        String userAgent = servletRequest.getHeader("User-Agent");

        BlockCard blockCard = new BlockCard(clientIp, userAgent, card);
        card.setBlock(StatusBlock.BLOQUEADO);
        card.addBlockCard(blockCard);

        // Atualiza o card e salva o blockCard por meio do cascade MERGE
        cardRepository.save(card);

        return ResponseEntity.ok().build();
    }

    private Card checkPossibleCard(String idCard) {
        Optional<Card> possibleCard = cardRepository.findById(idCard);

        if (possibleCard.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "O Cartão informado não existe");
        }
        return possibleCard.get();
    }

    /**
     * Função para pegar o ip do client que realizou a request,
     * o header x-forwarded-for é para pegar o ip real do client caso
     * o mesmo utilize algum proxy, caso o seu retorno seja null,
     * é ip do client é buscado com o getRemoteAddr
     */
    private String getClientIp() {
        String clientIp = servletRequest.getHeader("X-FORWARDED-FOR");

        if (clientIp == null) {
            clientIp = servletRequest.getRemoteAddr();
        }
        return clientIp;
    }


}

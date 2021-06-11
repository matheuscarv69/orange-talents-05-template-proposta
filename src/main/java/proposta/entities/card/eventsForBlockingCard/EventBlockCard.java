package proposta.entities.card.eventsForBlockingCard;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import proposta.core.feignClients.accounts.client.AccountsClient;
import proposta.core.feignClients.accounts.request.ResponsibleSystemReq;
import proposta.core.feignClients.accounts.response.NotifyCardBlockingRes;
import proposta.entities.card.controllers.BlockCardController;
import proposta.entities.card.entities.BlockCard;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.StatusBlock;
import proposta.entities.card.repositories.CardRepository;

import javax.servlet.http.HttpServletRequest;

@Primary
@Component
public class EventBlockCard implements EventsForBlockCard {

    private final Logger logger = LoggerFactory.getLogger(BlockCardController.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private AccountsClient accountsClient;

    /**
     * Envia a notificacao de bloqueio de cartao ao sistema de cartoes
     * e salva o bloqueio em caso de sucesso
     * */
    @Override
    public void sendNotificationCardBlocking(String idCard, Card card) {
        try {
            NotifyCardBlockingRes notifyCardBlockingRes = accountsClient.notifyCardBlocking(idCard,
                    new ResponsibleSystemReq("api-propostas"));

            logger.info("O status de bloqueio retornado do client foi {}", notifyCardBlockingRes.getStatusBlockCardRes());

            String clientIp = getClientIp();
            String userAgent = servletRequest.getHeader("User-Agent");

            BlockCard blockCard = new BlockCard(clientIp, userAgent, card);
            card.setBlock(StatusBlock.BLOQUEADO);
            card.addBlockCard(blockCard);

            // Atualiza o card e salva o blockCard por meio do cascade MERGE
            cardRepository.save(card);

            logger.info("Cartao bloqueado com sucesso, status:{}", card.getStatusBlock());
        } catch (FeignException exception) {
            logger.warn("Deu erro no envio da notificação de bloqueio de cartao");
            exception.printStackTrace();
        }
    }

    /**
     * Função para pegar o ip do client que realizou a request,
     * o header x-forwarded-for é para pegar o ip real do client caso
     * o mesmo utilize algum proxy, caso o seu retorno seja null,
     * é ip do client é buscado com o getRemoteAddr
     */
    @Override
    public String getClientIp() {
        String clientIp = servletRequest.getHeader("X-FORWARDED-FOR");

        if (clientIp == null) {
            clientIp = servletRequest.getRemoteAddr();
        }

        return clientIp;
    }
}
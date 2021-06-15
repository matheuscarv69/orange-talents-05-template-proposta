package proposta.entities.card.events.eventForTravelNotice;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.core.feignClients.accounts.client.AccountsClient;
import proposta.core.feignClients.accounts.request.NotifyTravelNoticeReq;
import proposta.core.feignClients.accounts.response.NotifyTravelNoticeRes;
import proposta.entities.card.controllers.BlockCardController;
import proposta.entities.card.entities.Card;
import proposta.entities.card.entities.TravelNotice;
import proposta.entities.card.repositories.TravelNoticeRepository;
import proposta.entities.card.request.TravelNoticeReq;
import proposta.entities.card.utils.GetDatasRequest;

@Primary
@Component
public class EventTravelNotice implements EventsForTravelNotice {

    private final Logger logger = LoggerFactory.getLogger(BlockCardController.class);

    @Autowired
    private TravelNoticeRepository travelNoticeRepository;

    @Autowired
    private GetDatasRequest dataRequest;

    @Autowired
    private AccountsClient accountsClient;

    /**
     * Envia a notificacao de aviso de viagem de cartao ao sistema de accounts
     * e salva o aviso em caso de sucesso
     */
    @Override
    public void sendNotificationTravelNotice(Card card,
                                             TravelNoticeReq travelNoticeCardReq) {

        try {
            NotifyTravelNoticeReq notifyTravelNoticeReq = travelNoticeCardReq.toNotify();
            NotifyTravelNoticeRes notifyTravelNoticeRes = accountsClient.notifyTravelNotice(card.getId(),
                    notifyTravelNoticeReq);
            logger.info("O status de aviso de viagem retornado do client foi {}", notifyTravelNoticeRes.getResultado());

            String clientIp = dataRequest.getClientIp();
            String userAgent = dataRequest.getUserAgent();

            TravelNotice travelNoticeCard = travelNoticeCardReq.toModel(card, clientIp, userAgent);
            travelNoticeRepository.save(travelNoticeCard);
        } catch (FeignException exception) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Ocorreu um erro no Sistema de cartões ao enviar o aviso de viagem");
        }


    }


}

package proposta.entities.card.events.eventForTravelNotice;

import proposta.entities.card.entities.Card;
import proposta.entities.card.request.TravelNoticeReq;

public interface EventsForTravelNotice {

    void sendNotificationTravelNotice(Card card, TravelNoticeReq travelNoticeCardReq);

}

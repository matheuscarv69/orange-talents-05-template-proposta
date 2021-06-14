package proposta.entities.card.events.eventForBlockingCard;

import proposta.entities.card.entities.Card;

public interface EventsForBlockCard {

    void sendNotificationCardBlocking(String idCard, Card card);

}

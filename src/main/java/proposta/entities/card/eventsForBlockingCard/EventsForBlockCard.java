package proposta.entities.card.eventsForBlockingCard;

import proposta.entities.card.entities.Card;

public interface EventsForBlockCard {

    void sendNotificationCardBlocking(String idCard, Card card);

}

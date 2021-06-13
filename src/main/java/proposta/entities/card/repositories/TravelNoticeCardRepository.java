package proposta.entities.card.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.card.entities.TravelNoticeCard;

public interface TravelNoticeCardRepository extends JpaRepository<TravelNoticeCard, Long> {

}

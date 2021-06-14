package proposta.entities.card.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.card.entities.TravelNotice;

public interface TravelNoticeRepository extends JpaRepository<TravelNotice, Long> {

}

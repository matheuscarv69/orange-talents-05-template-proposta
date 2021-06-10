package proposta.entities.card.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.card.entities.Card;

public interface CardRepository extends JpaRepository<Card, String> {
}

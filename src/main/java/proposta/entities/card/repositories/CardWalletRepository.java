package proposta.entities.card.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.card.entities.CardWallet;

public interface CardWalletRepository extends JpaRepository<CardWallet, Long> {
}

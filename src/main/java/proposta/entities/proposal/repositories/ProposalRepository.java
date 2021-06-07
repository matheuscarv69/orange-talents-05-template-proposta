package proposta.entities.proposal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.proposal.entities.Proposal;

import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findByDocument(String document);
}

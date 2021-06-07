package proposta.entities.proposal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.proposal.entities.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}

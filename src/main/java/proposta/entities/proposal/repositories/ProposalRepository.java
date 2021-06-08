package proposta.entities.proposal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proposta.entities.proposal.entities.Proposal;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findByDocument(String document);

    @Query("select pro from Proposal pro where pro.statusProposal = 'ELEGIVEL' and pro.card is null ")
    List<Proposal> findElegiblesProposals();

}

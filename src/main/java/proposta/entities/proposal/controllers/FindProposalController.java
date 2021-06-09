package proposta.entities.proposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.repositories.ProposalRepository;
import proposta.entities.proposal.response.ProposalRes;

import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class FindProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProposalRes> findProposal(@PathVariable Long id) {
        Optional<Proposal> possibleProposal = proposalRepository.findById(id);

        if (possibleProposal.isPresent()) {
            return ResponseEntity.ok().body(new ProposalRes(possibleProposal.get()));
        }

        throw new ApiErrorException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
    }


}

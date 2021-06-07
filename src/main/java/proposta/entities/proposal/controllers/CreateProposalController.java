package proposta.entities.proposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import proposta.configs.validation.genericBodyReponse.GenericBodyResponseError;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.repositories.ProposalRepository;
import proposta.entities.proposal.requests.ProposalReq;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class CreateProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @PostMapping
    public ResponseEntity<?> createProposal(@RequestBody @Valid ProposalReq proposalReq, UriComponentsBuilder uriBuilder) {
        Optional<Proposal> optProposal = proposalRepository.findByDocument(proposalReq.getDocument());

        if (optProposal.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new GenericBodyResponseError(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Já existe uma proposta com o documento informado"));
        }

        Proposal proposal = proposalReq.toModel();
        proposalRepository.save(proposal);

        URI uri = uriBuilder.path("/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}

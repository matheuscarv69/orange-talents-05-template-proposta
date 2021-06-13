package proposta.entities.proposal.controllers;

import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.core.metrics.ProposalMetrics;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.repositories.ProposalRepository;
import proposta.entities.proposal.response.ProposalRes;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class FindProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalMetrics proposalMetrics;

    @GetMapping("/{id}")
    public ResponseEntity<ProposalRes> findProposal(@PathVariable Long id) {
        // pega a hora que o metodo eh chamado
        final Long startRequest = System.currentTimeMillis();

        Optional<Proposal> possibleProposal = proposalRepository.findById(id);

        if (possibleProposal.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
        }

        // pega a hora em que o termina a operacao
        final Long endRequest = System.currentTimeMillis();
        final long durationRequest = endRequest - startRequest;
        // expoe na metrica o tempo que a operacao levou
        proposalMetrics.getTimerConsultProposal(Duration.of(durationRequest, ChronoUnit.MILLIS));

        return ResponseEntity.ok().body(new ProposalRes(possibleProposal.get()));
    }


}

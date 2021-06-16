package proposta.entities.proposal.controllers;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import proposta.core.metrics.ProposalMetrics;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.events.EventForCreateProposal;
import proposta.entities.proposal.requests.ProposalReq;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposal")
public class CreateProposalController {

    private final Logger logger = LoggerFactory.getLogger(CreateProposalController.class);

    @Autowired
    private EventForCreateProposal eventForCreateProposal;

    @Autowired
    private ProposalMetrics proposalMetrics;

    @Autowired
    private Tracer tracer;

    @PostMapping
    public ResponseEntity<URI> createProposal(@RequestBody @Valid ProposalReq proposalReq,
                                              UriComponentsBuilder uriBuilder) {
        // Span para o Jaeger
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", proposalReq.getEmail());
        activeSpan.setBaggageItem("user.email", proposalReq.getEmail());

        Proposal proposal = eventForCreateProposal.createProposal(proposalReq);
        logger.info("Proposta id={}, solicitante={} criada com sucesso!", proposal.getId(), proposal.getName());

        // metrica - incrementa o contador de propostas criadas
        proposalMetrics.incrementProposalsCounter();

        URI uri = uriBuilder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}

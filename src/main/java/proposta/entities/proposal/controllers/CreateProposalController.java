package proposta.entities.proposal.controllers;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.core.feignClients.proposalAnalysis.client.ProposalAnalysisClient;
import proposta.core.feignClients.proposalAnalysis.request.ProposalAnalysisReq;
import proposta.core.feignClients.proposalAnalysis.response.ProposalAnalysisRes;
import proposta.core.metrics.ProposalMetrics;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.entities.StatusProposal;
import proposta.entities.proposal.repositories.ProposalRepository;
import proposta.entities.proposal.requests.ProposalReq;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class CreateProposalController {

    private final Logger logger = LoggerFactory.getLogger(CreateProposalController.class);

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalAnalysisClient proposalAnalysisClient;

    @Autowired
    private ProposalMetrics proposalMetrics;

    @PostMapping
    public ResponseEntity<URI> createProposal(@RequestBody @Valid ProposalReq proposalReq, UriComponentsBuilder uriBuilder) {
        Optional<Proposal> optProposal = proposalRepository.findByDocument(proposalReq.getDocument());

        // verifica se já existe uma proposta com o documento informado
        if (optProposal.isPresent()) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta com o documento informado");
        }

        Proposal proposal = proposalReq.toModel();
        proposalRepository.save(proposal);
        // metrica - incrementa o contador de propostas criadas
        proposalMetrics.incrementProposalsCounter();

        logger.info("Proposta id={}, solicitante={} criada com sucesso!", proposal.getId(), proposal.getName());

        // analisa se solicitante esta elegivel a ter um cartao, analise feita em api externa
        sendRequestAnalysisProposal(proposal);

        URI uri = uriBuilder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private void sendRequestAnalysisProposal(Proposal proposal) {
        logger.info("Solicitante sera analisado pela api de analise de proposta");

        try {
            ProposalAnalysisRes analysisRes = proposalAnalysisClient.analyzeProposal(new ProposalAnalysisReq(proposal));
            proposal.setStatusProposal(analysisRes.getStatusProposalStandardized());

        } catch (FeignException.UnprocessableEntity exception) {
            logger.warn("Solicitante analisado com restricao, status da proposta definido como nao elegivel");
            proposal.setStatusProposal(StatusProposal.NAO_ELEGIVEL);

        } catch (FeignException exception) {
            logger.error("Algo de muito ruim aconteceu na analise do solicitante na api analise de proposta");
        }

        // atualiza proposta com o status referente a possibilidade de obter um cartao
        logger.info("Proposta id={}, solicitante={} atualizada com o status={} com sucesso!", proposal.getId(), proposal.getName(), proposal.getStatusProposal());
        proposalRepository.save(proposal);
    }


}

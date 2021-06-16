package proposta.entities.proposal.events;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.core.feignClients.proposalAnalysis.client.ProposalAnalysisClient;
import proposta.core.feignClients.proposalAnalysis.request.ProposalAnalysisReq;
import proposta.core.feignClients.proposalAnalysis.response.ProposalAnalysisRes;
import proposta.entities.proposal.controllers.CreateProposalController;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.entities.StatusProposal;
import proposta.entities.proposal.repositories.ProposalRepository;
import proposta.entities.proposal.requests.ProposalReq;

import java.util.Optional;

import static proposta.entities.proposal.utils.EncryptorUtils.textEncrypt;

@Primary
@Component
public class EventForCreateProposal implements EventsForCreateProposal {

    private final Logger logger = LoggerFactory.getLogger(CreateProposalController.class);

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProposalAnalysisClient proposalAnalysisClient;

    @Override
    public Proposal createProposal(ProposalReq proposalReq) {

        // Criptografa o documento vindo da requisicao
        String documentEncrypted = textEncrypt(proposalReq.getDocument());

        // verifica se já existe uma proposta com o documento informado
        checkDocumentExists(documentEncrypted);

        Proposal proposal = proposalReq.toModel(documentEncrypted);
        proposalRepository.save(proposal);

        // analisa se solicitante esta elegivel a ter um cartao, analise feita em api externa
        sendRequestAnalysisProposal(proposal);

        return proposal;
    }

    @Override
    public void checkDocumentExists(String document) {
        Optional<Proposal> optProposal = proposalRepository.findByDocument(document);

        // verifica se já existe uma proposta com o documento informado
        if (optProposal.isPresent()) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta com o documento informado");
        }
    }

    @Override
    public void sendRequestAnalysisProposal(Proposal proposal) {
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

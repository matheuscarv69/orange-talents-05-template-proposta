package proposta.core.feignClients.proposalAnalysis.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import proposta.core.feignClients.proposalAnalysis.request.ProposalAnalysisReq;
import proposta.core.feignClients.proposalAnalysis.response.ProposalAnalysisRes;

@FeignClient(name = "ProposalAnalysis", url = "${api-analysis-proposal}" )
public interface ProposalAnalysisClient {

    @PostMapping()
    ProposalAnalysisRes analyzeProposal(ProposalAnalysisReq proposalAnalysisReq);

}

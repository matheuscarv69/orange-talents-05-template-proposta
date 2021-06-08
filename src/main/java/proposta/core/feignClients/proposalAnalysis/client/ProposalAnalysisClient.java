package proposta.core.feignClients.proposalAnalysis.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import proposta.core.feignClients.proposalAnalysis.request.ProposalAnalysisReq;
import proposta.core.feignClients.proposalAnalysis.response.ProposalAnalysisRes;

@FeignClient(name = "ProposalAnalysis-Client", url = "${api-analysis-proposal}" )
public interface ProposalAnalysisClient {

    // consumes e produces por padrao sao application/json
    @PostMapping()
    ProposalAnalysisRes analyzeProposal(ProposalAnalysisReq proposalAnalysisReq);

}

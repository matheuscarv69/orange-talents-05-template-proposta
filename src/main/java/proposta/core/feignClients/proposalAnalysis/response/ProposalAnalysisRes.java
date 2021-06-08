package proposta.core.feignClients.proposalAnalysis.response;

import proposta.core.feignClients.proposalAnalysis.entities.StatusProposalAnalysis;
import proposta.entities.proposal.entities.StatusProposal;

public class ProposalAnalysisRes {

    private String documento;
    private String nome;
    private StatusProposalAnalysis resultadoSolicitacao;
    private String idProposta;

    public ProposalAnalysisRes(String documento,
                               String nome,
                               StatusProposalAnalysis resultadoSolicitacao,
                               String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public StatusProposal getStatusProposalStandardized() {
        return resultadoSolicitacao.standardize();
    }

}

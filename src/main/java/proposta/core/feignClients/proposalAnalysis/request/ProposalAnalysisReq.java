package proposta.core.feignClients.proposalAnalysis.request;

import proposta.entities.proposal.entities.Proposal;

public class ProposalAnalysisReq {

    private String documento;
    private String nome;
    private String idProposta;

    public ProposalAnalysisReq(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}

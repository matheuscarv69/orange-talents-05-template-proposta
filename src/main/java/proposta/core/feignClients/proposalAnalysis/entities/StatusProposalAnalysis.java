package proposta.core.feignClients.proposalAnalysis.entities;

import proposta.entities.proposal.entities.StatusProposal;

public enum StatusProposalAnalysis {

    COM_RESTRICAO,
    SEM_RESTRICAO;

    public StatusProposal standardize() {
        if (this.equals(COM_RESTRICAO)) {
            return StatusProposal.NAO_ELEGIVEL;
        }
        return StatusProposal.ELEGIVEL;
    }

}

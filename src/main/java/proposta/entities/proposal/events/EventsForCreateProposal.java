package proposta.entities.proposal.events;

import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.requests.ProposalReq;

public interface EventsForCreateProposal {

    Proposal createProposal(ProposalReq proposalReq);

    void checkDocumentExists(String document);

    void sendRequestAnalysisProposal(Proposal proposal);

}

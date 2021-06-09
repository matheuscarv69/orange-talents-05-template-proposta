package proposta.entities.proposal.response;

import proposta.entities.card.entities.Card;
import proposta.entities.proposal.entities.Proposal;
import proposta.entities.proposal.entities.StatusProposal;

import java.math.BigDecimal;

public class ProposalRes {

    private String name;
    private String email;
    private String document;
    private String address;
    private BigDecimal salary;
    private String statusProposal;
    private CardProposalRes card;

    public ProposalRes(Proposal proposal) {
        this.name = proposal.getName();
        this.email = proposal.getEmail();
        this.document = proposal.getDocument();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.statusProposal = statusProposalToString(proposal.getStatusProposal());
        this.card = cardToCardProposalRes(proposal.getCard());
    }

    private String statusProposalToString(StatusProposal statusProposal) {
        if (statusProposal == null) {
            return "Proposta ainda não foi analisada";
        }
        return statusProposal.toString();
    }

    private CardProposalRes cardToCardProposalRes(Card card) {
        if (card == null) {
            return new CardProposalRes();
        }

        return new CardProposalRes(card);
    }

    // Getter used by jackson for serializable

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getStatusProposal() {
        return statusProposal;
    }

    public CardProposalRes getCard() {
        return card;
    }
}

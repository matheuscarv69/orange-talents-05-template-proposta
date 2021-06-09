package proposta.entities.proposal.response;

import proposta.entities.card.entities.Card;

public class CardProposalRes {

    private String createdAt;
    private String owner;
    private String credit_limit;
    private DueDateCardProposalRes dueDate;

    public CardProposalRes() {
        this.createdAt = "";
        this.owner = "";
        this.credit_limit = "";
        this.dueDate = new DueDateCardProposalRes();
    }

    public CardProposalRes(Card card) {
        this.createdAt = card.getCreatedAt().toString();
        this.owner = card.getOwner();
        this.credit_limit = card.getCredit_limit().toString();
        this.dueDate = new DueDateCardProposalRes(card.getDueDate());
    }

    // Getter used by jackson for serializable

    public String getCreatedAt() {
        return createdAt;
    }

    public String getOwner() {
        return owner;
    }

    public String getCredit_limit() {
        return credit_limit;
    }

    public DueDateCardProposalRes getDueDate() {
        return dueDate;
    }
}

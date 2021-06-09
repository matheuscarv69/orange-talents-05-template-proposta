package proposta.entities.proposal.response;

import proposta.entities.card.entities.DueDate;

public class DueDateCardProposalRes {

    private String day;
    private String createAt;

    public DueDateCardProposalRes() {
        this.day = "";
        this.createAt = "";
    }

    public DueDateCardProposalRes(DueDate dueDate) {
        this.day = dueDate.getDay().toString();
        this.createAt = dueDate.getCreateAt().toString();
    }

    // Getter used by jackson for serializable

    public String getDay() {
        return day;
    }

    public String getCreateAt() {
        return createAt;
    }
}

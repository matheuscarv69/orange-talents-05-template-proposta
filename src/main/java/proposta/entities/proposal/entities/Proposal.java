package proposta.entities.proposal.entities;

import proposta.configs.validation.cpfOrcnpj.CpfOrCpnj;
import proposta.entities.card.entities.Card;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @CpfOrCpnj
    @Column(nullable = false)
    private String document;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private StatusProposal statusProposal;

    @OneToOne(cascade = CascadeType.MERGE)
    private Card card;

    public Proposal(String name,
                    String email,
                    String document,
                    String address,
                    BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.address = address;
        this.salary = salary;
    }

    // only hibernate
    @Deprecated
    public Proposal() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public StatusProposal getStatusProposal() {
        return statusProposal;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Card getCard() {
        return card;
    }

    public void setStatusProposal(StatusProposal statusProposal) {
        this.statusProposal = statusProposal;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

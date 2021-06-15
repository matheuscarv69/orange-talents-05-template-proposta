package proposta.entities.proposal.requests;

import proposta.configs.validation.cpfOrcnpj.CpfOrCpnj;
import proposta.entities.proposal.entities.Proposal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProposalReq {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CpfOrCpnj
    private String document;

    @NotBlank
    private String address;

    @NotNull
    @Positive
    private BigDecimal salary;

    public ProposalReq(String name,
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

    public Proposal toModel() {
        return new Proposal(name, email, document, address, salary);
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return this.document;
    }
}

package proposta.entities.proposal.entities;

import proposta.configs.validation.cpfOrcnpj.CpfOrCpnj;

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
}

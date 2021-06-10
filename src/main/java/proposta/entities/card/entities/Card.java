package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.biometry.controllers.Biometry;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Card {

    @Id
    private String id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private BigDecimal credit_limit;

    @ManyToOne(cascade = CascadeType.MERGE)
    private DueDate dueDate;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private Set<Biometry> biometries = new HashSet<>();

    // only hibernate
    @Deprecated
    public Card() {
    }

    public Card(String id,
                LocalDateTime createdAt,
                String owner,
                BigDecimal credit_limit,
                DueDate dueDate) {
        this.id = id;
        this.createdAt = createdAt;
        this.owner = owner;
        this.credit_limit = credit_limit;
        this.dueDate = dueDate;
    }

    public void addBiometry(Biometry biometry) {

        biometries.forEach(biometrie -> {
            if (biometrie.equals(biometry)) {
                throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Fingerprint já está associada cadastrada");
            }
        });

        this.biometries.add(biometry);
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

}

package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

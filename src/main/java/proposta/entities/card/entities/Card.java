package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import proposta.configs.exception.customExceptions.ApiErrorException;
import proposta.entities.biometry.entities.Biometry;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Card {

    @Id
    private String id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @NotBlank
    @Column(nullable = false)
    private String owner;

    @NotNull
    @Column(nullable = false)
    private BigDecimal credit_limit;

    @ManyToOne(cascade = CascadeType.MERGE)
    private DueDate dueDate;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private Set<Biometry> biometries = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusBlock statusBlock;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<BlockCard> blockCardList = new ArrayList<>();

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
        this.statusBlock = StatusBlock.NAO_BLOQUEADO;
    }

    public void addBiometry(Biometry biometry) {

        biometries.forEach(biometrie -> {
            if (biometrie.equals(biometry)) {
                throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Fingerprint já está associada cadastrada");
            }
        });

        this.biometries.add(biometry);
    }

    public void setBlock(StatusBlock statusBlock) {
        this.statusBlock = statusBlock;
    }

    public boolean isBlock() {
        return statusBlock.equals(StatusBlock.BLOQUEADO);
    }

    public void addBlockCard(BlockCard blockCard) {
        this.blockCardList.add(blockCard);
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

    public StatusBlock getStatusBlock() {
        return statusBlock;
    }
}

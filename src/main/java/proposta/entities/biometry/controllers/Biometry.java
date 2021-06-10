package proposta.entities.biometry.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import proposta.entities.card.entities.Card;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String fingerprint;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @ManyToOne
    private Card card;

    public Biometry(String fingerprint,
                    Card card) {
        this.fingerprint = fingerprint;
        this.createdAt = LocalDateTime.now();
        this.card = card;
    }

    // only hibernate
    @Deprecated
    public Biometry() {
    }

    public Long getId() {
        return id;
    }

    // equals and hashcode for Set in card

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biometry)) return false;
        Biometry biometry = (Biometry) o;
        return fingerprint.equals(biometry.fingerprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerprint);
    }


}
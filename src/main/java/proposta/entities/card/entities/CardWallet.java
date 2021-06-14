package proposta.entities.card.entities;

import proposta.entities.card.entities.enums.Wallets;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class CardWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Wallets wallet;

    public CardWallet(String email,
                      Wallets wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    // only hibernate
    @Deprecated
    public CardWallet() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardWallet)) return false;
        CardWallet that = (CardWallet) o;
        return wallet == that.wallet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallet);
    }
}

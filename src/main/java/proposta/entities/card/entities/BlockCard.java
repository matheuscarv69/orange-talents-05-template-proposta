package proposta.entities.card.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class BlockCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String clientIp;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @ManyToOne
    private Card card;

    public BlockCard(String clientIp, String userAgent, Card card) {

        this.clientIp = clientIp;
        this.userAgent = userAgent;
        this.card = card;
    }

    // only hibernate
    @Deprecated
    public BlockCard() {
    }

}

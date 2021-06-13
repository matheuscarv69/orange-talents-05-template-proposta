package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class BlockCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @NotBlank
    @Column(nullable = false)
    private String clientIp;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @ManyToOne
    private Card card;

    public BlockCard(String clientIp,
                     String userAgent,
                     Card card) {
        this.createdAt = LocalDateTime.now();
        this.clientIp = clientIp;
        this.userAgent = userAgent;
        this.card = card;
    }

    // only hibernate
    @Deprecated
    public BlockCard() {
    }

}

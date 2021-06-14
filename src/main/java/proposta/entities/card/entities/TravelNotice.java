package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TravelNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String travelDestination;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime beginTravel;

    @NotNull
    @Future
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate endTravel;

    @ManyToOne
    private Card card;

    @NotBlank
    private String clientIp;

    @NotBlank
    private String userAgent;

    public TravelNotice(String destinoViagem,
                        LocalDate fimViagem,
                        Card card,
                        String clientIp,
                        String userAgent) {
        this.travelDestination = destinoViagem;
        this.beginTravel = LocalDateTime.now();
        this.endTravel = fimViagem;
        this.card = card;
        this.clientIp = clientIp;
        this.userAgent = userAgent;
    }

    // only hibernate
    @Deprecated
    public TravelNotice() {
    }

}

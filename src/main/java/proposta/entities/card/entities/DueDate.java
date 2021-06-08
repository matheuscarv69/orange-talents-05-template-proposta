package proposta.entities.card.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class DueDate {

    @Id
    private String id;

    @Positive
    @NotNull
    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createAt;

    // only hibernate
    @Deprecated
    public DueDate() {
    }

    public DueDate(String id, Integer day, LocalDateTime createAt) {
        this.id = id;
        this.day = day;
        this.createAt = createAt;
    }

}

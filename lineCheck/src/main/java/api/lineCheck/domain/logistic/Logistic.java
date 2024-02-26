package api.lineCheck.domain.logistic;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "logistics")
@Entity(name = "Logistic")
public class Logistic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String logistic;
    private List<UUID> workListIds;
    private Logistic (String data) {
        this.logistic = data;
    }
    public static Logistic create(String data) {
        return new Logistic(data);
    }
}

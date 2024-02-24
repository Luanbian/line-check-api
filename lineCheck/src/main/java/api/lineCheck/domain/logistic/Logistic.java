package api.lineCheck.domain.logistic;

import api.lineCheck.domain.work.Work;
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
    @OneToMany(mappedBy = "logistic", fetch = FetchType.LAZY)
    private List<Work> workList;
}

package api.lineCheck.domain.manufacture;

import api.lineCheck.domain.work.Work;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Manufacture")
@Table(name = "Manufactures")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String manufacture;
    @OneToMany(mappedBy = "manufacture")
    private List<Work> works;
}
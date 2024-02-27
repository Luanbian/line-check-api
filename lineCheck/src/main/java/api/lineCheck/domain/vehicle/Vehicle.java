package api.lineCheck.domain.vehicle;

import api.lineCheck.domain.work.Work;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Vehicle")
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String vehicle;
    @OneToMany(mappedBy = "vehicle")
    private List<Work> works;
}

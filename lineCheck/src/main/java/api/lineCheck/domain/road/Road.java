package api.lineCheck.domain.road;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Road")
@Table(name = "Roads")
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String road;
}

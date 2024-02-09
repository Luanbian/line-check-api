package api.lineCheck.domain.manufactures;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Manufacture")
@Table(name = "Manufactures")
public class Manufactures {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String manufactures;
}
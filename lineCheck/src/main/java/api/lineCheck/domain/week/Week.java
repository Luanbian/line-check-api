package api.lineCheck.domain.week;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Week")
@Table(name = "Weeks")
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String week;
}
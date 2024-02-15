package api.lineCheck.domain.week;

import jakarta.persistence.*;
import lombok.*;
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
    private DaysOfTheWeek daysOfTheWeek;
}
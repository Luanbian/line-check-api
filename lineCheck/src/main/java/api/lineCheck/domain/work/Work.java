package api.lineCheck.domain.work;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "Work")
@Table(name = "works")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID accountId;
    private UUID serviceId;
    private List<DayOfWeek> daysOfTheWeek;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private Timestamp startJourneyReal;
    private Timestamp startLineReal;
    private Timestamp endLineReal;
    private Time timeWorkedModel;
    private Time timeWorkedReal;
    private UUID logisticId;
    private UUID vehicleId;
    private UUID manufactureId;
    private Work(WorkProps props) {
        this.accountId = props.accountId();
        this.serviceId = props.serviceId();
        this.daysOfTheWeek = props.daysOfTheWeeks().stream()
                .map(day -> DayOfWeek.of(day.getValue() % 7 + 1))
                .toList();
        this.startJourneyModel = props.startJourneyModel();
        this.startLineModel = props.startLineModel();
        this.endLineModel = props.endLineModel();
        this.logisticId = props.logisticId();
        this.vehicleId = props.vehicleId();
        this.manufactureId = props.manufactureId();
    }
    public static Work create(WorkProps props) {
        return new Work(props);
    }
}

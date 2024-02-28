package api.lineCheck.domain.work;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.service.DriverService;
import api.lineCheck.domain.vehicle.Vehicle;
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

    @Column(name = "account_id")
    private UUID accountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false, referencedColumnName = "id")
    private Account account;

    @Column(name = "service_id")
    private UUID serviceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private DriverService service;

    private List<DayOfWeek> daysOfTheWeek;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private Timestamp startJourneyReal;
    private Timestamp startLineReal;
    private Timestamp endLineReal;
    private Time timeWorkedModel;
    private Time timeWorkedReal;

    @Column(name = "logistic_id")
    private UUID logisticId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_id", insertable = false, updatable = false)
    private Logistic logistic;

    @Column(name = "vehicle_id")
    private UUID vehicleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;

    @Column(name = "manufacture_id")
    private UUID manufactureId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id", insertable = false, updatable = false)
    private Manufacture manufacture;
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
        this.timeWorkedModel = calculateTotalTimeWorkedModel();
    }
    public static Work create(WorkProps props) {
        return new Work(props);
    }
    private Time calculateTotalTimeWorkedModel() {
        long total = this.endLineModel.getTime() - this.startJourneyModel.getTime();
        return new Time(total);
    }
}

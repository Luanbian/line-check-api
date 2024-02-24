package api.lineCheck.domain.work;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.service.DriverService;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.domain.week.DaysOfTheWeek;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnoreProperties("workList")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @JsonIgnoreProperties("workList")
    private DriverService service;
    private List<DaysOfTheWeek> daysOfTheWeek;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private Timestamp startJourneyReal;
    private Timestamp startLineReal;
    private Timestamp endLineReal;
    private Time timeWorkedModel;
    private Time timeWorkedReal;
    @ManyToOne
    @JoinColumn(name = "logistic_id", referencedColumnName = "id")
    @JsonIgnoreProperties("workList")
    private Logistic logistic;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @JsonIgnoreProperties("workList")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id")
    @JsonIgnoreProperties("workList")
    private Manufacture manufacture;
    private Work(WorkProps props) {
        this.account = props.account();
        this.service = props.service();
        this.daysOfTheWeek = props.daysOfTheWeeks();
        this.startJourneyModel = props.startJourneyModel();
        this.startLineModel = props.startLineModel();
        this.endLineModel = props.endLineModel();
        this.logistic = props.logistic();
        this.vehicle = props.vehicle();
        this.manufacture = props.manufacture();
    }
    public static Work create(WorkProps props) {
        return new Work(props);
    }
}

package api.lineCheck.domain.work;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.service.Service;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.domain.week.DaysOfTheWeek;
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
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;
    private List<DaysOfTheWeek> daysOfTheWeek;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private Timestamp startJourneyReal;
    private Timestamp startLineReal;
    private Timestamp endLineReal;
    @ManyToOne
    @JoinColumn(name = "logistic_id", referencedColumnName = "id")
    private Logistic logistic;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id")
    private Manufacture manufacture;
}

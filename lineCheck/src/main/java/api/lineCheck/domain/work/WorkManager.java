package api.lineCheck.domain.work;

import api.lineCheck.domain.week.DaysOfTheWeek;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
@Data
public class WorkManager {
    private UUID id;
    private String accountName;
    private Time startJourneyModel;
    private Timestamp startJourneyReal;
    private Time startLineModel;
    private Timestamp startLineReal;
    private Time endLineModel;
    private Timestamp endLineReal;
    private String service;
    private String logistic;
    private String manufacture;
    private String vehicle;
    private List<DaysOfTheWeek> daysOfTheWeek;
    public WorkManager(
            UUID id, String name, Time startJourney, Timestamp startJourneyReal,
            Time startLine, Timestamp startLineReal, Time endLine, Timestamp endLineReal,
            String se, String lo, String man, String ve, List<DaysOfTheWeek> days
    ) {
        this.id = id;
        this.accountName = name;
        this.startJourneyModel = startJourney;
        this.startJourneyReal = startJourneyReal;
        this.startLineModel = startLine;
        this.startLineReal = startLineReal;
        this.endLineModel = endLine;
        this.endLineReal = endLineReal;
        this.service = se;
        this.logistic = lo;
        this.manufacture = man;
        this.vehicle = ve;
        this.daysOfTheWeek = days;
    }
}

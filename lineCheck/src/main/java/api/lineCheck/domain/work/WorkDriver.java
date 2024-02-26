package api.lineCheck.domain.work;

import lombok.Data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Data
public class WorkDriver {
    private UUID id;
    private String accountName;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private String service;
    private String logistic;
    private String manufacture;
    private String vehicle;
    private List<DayOfWeek> daysOfTheWeek;
    public WorkDriver(
            UUID id, String name, Time startJourney, Time startLine, Time endLine,
            String se, String lo, String man, String ve, List<DayOfWeek> days
    ) {
        this.id = id;
        this.accountName = name;
        this.startJourneyModel = startJourney;
        this.startLineModel = startLine;
        this.endLineModel = endLine;
        this.service = se;
        this.logistic = lo;
        this.manufacture = man;
        this.vehicle = ve;
        this.daysOfTheWeek = days;
    }
}

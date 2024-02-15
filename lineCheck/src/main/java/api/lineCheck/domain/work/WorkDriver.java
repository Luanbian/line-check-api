package api.lineCheck.domain.work;

import api.lineCheck.domain.week.DaysOfTheWeek;
import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class WorkDriver {
    private String accountName;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endLineModel;
    private String service;
    private String logistic;
    private String manufacture;
    private String vehicle;
    private List<DaysOfTheWeek> daysOfTheWeek;
    public WorkDriver(
            String name, Time startJourney, Time startLine, Time endLine,
            String se, String lo, String man, String ve, List<DaysOfTheWeek> days
    ) {
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

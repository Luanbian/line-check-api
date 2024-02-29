package api.lineCheck.mocks;

import api.lineCheck.domain.work.WorkProps;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WorkPropsMock {
    public UUID accountId;
    public UUID serviceId;
    public List<DayOfWeek> daysOfTheWeeks;
    public Time startJourneyModel;
    public Time startLineModel;
    public Time endLineModel;
    public UUID logisticId;
    public UUID vehicleId;
    public UUID manufactureId;
    public WorkPropsMock() {
        Faker faker = new Faker();
        LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        List<DayOfWeek> daysOfTheWeeks = new ArrayList<>();
        daysOfTheWeeks.add(DayOfWeek.SUNDAY);
        daysOfTheWeeks.add(DayOfWeek.MONDAY);
        daysOfTheWeeks.add(DayOfWeek.TUESDAY);
        daysOfTheWeeks.add(DayOfWeek.WEDNESDAY);
        daysOfTheWeeks.add(DayOfWeek.THURSDAY);
        daysOfTheWeeks.add(DayOfWeek.FRIDAY);
        daysOfTheWeeks.add(DayOfWeek.SATURDAY);
        this.accountId = UUID.randomUUID();
        this.serviceId = UUID.randomUUID();
        this.daysOfTheWeeks = daysOfTheWeeks;
        this.startJourneyModel = Time.valueOf(localTime);
        this.startLineModel = Time.valueOf(localTime);
        this.endLineModel = Time.valueOf(localTime);
        this.logisticId = UUID.randomUUID();
        this.vehicleId = UUID.randomUUID();
        this.manufactureId = UUID.randomUUID();
    }
    public WorkProps main() {
        return new WorkProps(
                accountId,serviceId,daysOfTheWeeks,startJourneyModel,
                startLineModel,endLineModel,logisticId,vehicleId,manufactureId
        );
    }
}

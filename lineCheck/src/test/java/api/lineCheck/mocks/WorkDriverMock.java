package api.lineCheck.mocks;

import api.lineCheck.domain.work.WorkDriver;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WorkDriverMock {
    public UUID id;
    public String accountName;
    public Time startJourneyModel;
    public Time startLineModel;
    public Time endLineModel;
    public String service;
    public String logistic;
    public String manufacture;
    public String vehicle;
    public List<DayOfWeek> daysOfTheWeek;
    public WorkDriverMock() {
        Faker faker = new Faker();
        LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        this.id = UUID.randomUUID();
        this.accountName = faker.name().firstName();
        this.startJourneyModel = Time.valueOf(localTime);
        this.startLineModel = Time.valueOf(localTime);
        this.endLineModel = Time.valueOf(localTime);
        this.service = faker.harryPotter().spell();
        this.logistic = faker.lordOfTheRings().location();
        this.manufacture = faker.company().name();
        this.vehicle = faker.aviation().airport();
        this.daysOfTheWeek = new ArrayList<>();
        this.daysOfTheWeek.add(faker.options().option(DayOfWeek.class));
    }

    public WorkDriver main() {
        return new WorkDriver(
                id, accountName, startJourneyModel, startLineModel, endLineModel,
                service, logistic, manufacture, vehicle, daysOfTheWeek
        );
    }
}

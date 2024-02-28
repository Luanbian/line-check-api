package api.lineCheck.mocks;

import api.lineCheck.core.dtos.WorkDto;
import com.github.javafaker.Faker;
import lombok.Data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
public class WorkDtoMock {
    public String accountId;
    public String serviceId;
    public List<String> daysOfTheWeeks;
    public String startJourneyModel;
    public String startLineModel;
    public String endLineModel;
    public String logisticId;
    public String vehicleId;
    public String manufactureId;
    public WorkDtoMock() {
        Faker faker = new Faker();
        LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        Time time = Time.valueOf(localTime);
        this.accountId = UUID.randomUUID().toString();
        this.serviceId = UUID.randomUUID().toString();
        this.daysOfTheWeeks = new ArrayList<>();
        daysOfTheWeeks.add(faker.options().option(DayOfWeek.class).toString());
        daysOfTheWeeks.add(faker.options().option(DayOfWeek.class).toString());
        this.startJourneyModel = time.toString();
        this.startLineModel = time.toString();
        this.endLineModel = time.toString();
        this.logisticId = UUID.randomUUID().toString();
        this.vehicleId = UUID.randomUUID().toString();
        this.manufactureId = UUID.randomUUID().toString();
    }
    public WorkDto main() {
        return new WorkDto(
                accountId, serviceId, daysOfTheWeeks, startJourneyModel,
                startLineModel, endLineModel, logisticId, vehicleId, manufactureId
        );
    }
}

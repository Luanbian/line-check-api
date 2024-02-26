package api.lineCheck.mocks;

import api.lineCheck.domain.work.WorkManager;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WorkManagerMock {
        public UUID id;
        public String accountName;
        public Time startJourneyModel;
        public Timestamp startJourneyReal;
        public Time startLineModel;
        public Timestamp startLineReal;
        public Time endLineModel;
        public Timestamp endLineReal;
        public String service;
        public String logistic;
        public String manufacture;
        public String vehicle;
        public List<DayOfWeek> daysOfTheWeek;
        public WorkManagerMock() {
            Faker faker = new Faker();
            LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);
            this.id = UUID.randomUUID();
            this.accountName = faker.name().firstName();
            this.startJourneyModel = Time.valueOf(localTime);
            this.startJourneyReal = Timestamp.valueOf(localDateTime);
            this.startLineModel = Time.valueOf(localTime);
            this.startLineReal = Timestamp.valueOf(localDateTime);
            this.endLineModel = Time.valueOf(localTime);
            this.endLineReal = Timestamp.valueOf(localDateTime);
            this.service = faker.harryPotter().spell();
            this.logistic = faker.lordOfTheRings().location();
            this.manufacture = faker.company().name();
            this.vehicle = faker.aviation().airport();
            this.daysOfTheWeek = new ArrayList<>();
            this.daysOfTheWeek.add(faker.options().option(DayOfWeek.class));
        }
        public WorkManager main() {
            return new WorkManager(
                    id, accountName, startJourneyModel, startJourneyReal, startLineModel,
                    startLineReal, endLineModel, endLineReal, service,
                    logistic, manufacture, vehicle, daysOfTheWeek
            );
        }
    }

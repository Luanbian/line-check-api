package api.lineCheck.mocks;

import api.lineCheck.data.enums.LineChecks;
import com.github.javafaker.Faker;
import lombok.Data;

import javax.sound.sampled.Line;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
public class PutRequestDriverMock {
    public String workId;
    public UUID workuuid;
    public String accountId;
    public String marker;
    public LineChecks line;
    public Timestamp endLineReal;
    public Timestamp startJourneyReal;
    public PutRequestDriverMock() {
        Faker faker = new Faker();
        LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        UUID random = UUID.randomUUID();
        this.workId = random.toString();
        this.workuuid = random;
        this.accountId = random.toString();
        this.marker = faker.options().option(LineChecks.class).toString();
        this.line = LineChecks.valueOf(marker);
        this.endLineReal = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), localTime));
        this.startJourneyReal = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), localTime));
    }
}

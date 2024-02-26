package api.lineCheck.mocks;

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

public class WorkManagerDbMock {
    public static List<Object[]> main() {
        Faker faker = new Faker();
        LocalTime localTime = faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        List<Object[]> data = new ArrayList<>();
        List<DayOfWeek> daysOfTheWeeks = new ArrayList<>();
        daysOfTheWeeks.add(faker.options().option(DayOfWeek.class));
        Object[] item = new Object[]{
                UUID.randomUUID(),
                "Ismael",
                Time.valueOf(localTime),
                Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), localTime)),
                Time.valueOf(localTime),
                Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), localTime)),
                Time.valueOf(localTime),
                Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), localTime)),
                "Entrada 2x2",
                "Vl.Angélica - Nova Sorocaba",
                "Bericap",
                "VAN 224",
                daysOfTheWeeks
        };
        data.add(item);
        return data;
    }
}

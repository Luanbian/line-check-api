package api.lineCheck.mocks;

import api.lineCheck.data.enums.LineChecks;
import com.github.javafaker.Faker;
import lombok.Data;

import javax.sound.sampled.Line;
import java.util.UUID;

@Data
public class PutRequestDriverMock {
    public String workId;
    public UUID workuuid;
    public String accountId;
    public String marker;
    public LineChecks line;
    public PutRequestDriverMock() {
        Faker faker = new Faker();
        UUID random = UUID.randomUUID();
        this.workId = random.toString();
        this.workuuid = random;
        this.accountId = random.toString();
        this.marker = faker.options().option(LineChecks.class).toString();
        this.line = LineChecks.valueOf(marker);
    }
}

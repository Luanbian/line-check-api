package api.lineCheck.mocks;

import api.lineCheck.data.enums.LineChecks;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class PutRequestDriverMock {
    public String workId;
    public String accountId;
    public String marker;
    public PutRequestDriverMock() {
        Faker faker = new Faker();
        this.workId = faker.idNumber().valid();
        this.accountId = faker.idNumber().valid();
        this.marker = faker.options().option(LineChecks.class).toString();
    }
}

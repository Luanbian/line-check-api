package api.lineCheck.mocks;

import api.lineCheck.core.dtos.DeviceTokenDto;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class DeviceTokenDtoMock {
    public String accountId;
    public String deviceToken;
    public DeviceTokenDtoMock() {
        Faker faker = new Faker();
        this.accountId = faker.starTrek().character();
        this.deviceToken = faker.phoneNumber().cellPhone();
    }
    public DeviceTokenDto main() {
        return new DeviceTokenDto(accountId, deviceToken);
    }
}

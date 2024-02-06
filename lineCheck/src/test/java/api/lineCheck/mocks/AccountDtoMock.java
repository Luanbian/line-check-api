package api.lineCheck.mocks;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.domain.Role;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class AccountDtoMock {
    public String name;
    public String email;
    public String phone;
    public String password;
    public String role;
    public AccountDtoMock () {
        Faker faker = new Faker();
        this.name = faker.name().firstName();
        this.email = faker.internet().emailAddress();
        this.phone = faker.phoneNumber().cellPhone();
        this.password = faker.internet().password();
        this.role = "DRIVER";
    }
    public AccountDto main () {

        return new AccountDto(name, email, phone, password, role);
    }
}

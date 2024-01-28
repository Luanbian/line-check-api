package api.lineCheck.mocks;

import api.lineCheck.domain.AccountProps;
import com.github.javafaker.Faker;

public class AccountPropsMock {
    public String name;
    public String email;
    public String phone;
    public String password;
    public AccountPropsMock () {
        Faker faker = new Faker();
        this.name = faker.name().firstName();
        this.email = faker.internet().emailAddress();
        this.phone = faker.phoneNumber().cellPhone();
        this.password = faker.internet().password();
    }
    public AccountProps main () {
        return new AccountProps(name, email, phone, password);
    }
}

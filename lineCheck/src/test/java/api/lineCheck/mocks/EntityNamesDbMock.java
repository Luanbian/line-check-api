package api.lineCheck.mocks;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityNamesDbMock {
    public static List<Object[]> main() {
        Faker faker = new Faker();
        List<Object[]> data = new ArrayList<>();
        Object[] item = new Object[]{
                UUID.randomUUID(),
                faker.beer().name(),
                faker.superhero().name(),
        };
        data.add(item);
        return data;
    }
}

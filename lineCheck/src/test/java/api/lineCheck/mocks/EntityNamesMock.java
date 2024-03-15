package api.lineCheck.mocks;

import api.lineCheck.data.utils.entities.EntityNames;
import com.github.javafaker.Faker;

import java.util.UUID;

public class EntityNamesMock {
    public UUID id;
    public String origin;
    public String name;
    public EntityNamesMock () {
        Faker faker = new Faker();
        this.id = UUID.randomUUID();
        this.origin = faker.beer().name();
        this.name = faker.superhero().name();
    }
    public EntityNames main() {
        return new EntityNames(id, origin, name);
    }
}

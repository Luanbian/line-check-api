package api.lineCheck.mocks;

import api.lineCheck.core.dtos.KmDto;
import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class KmDtoMock {
    public Integer initKm;
    public Integer finalKm;
    public KmDtoMock() {
        Faker faker = new Faker();
        this.initKm = faker.number().numberBetween(0, 200);
        this.finalKm = faker.number().numberBetween(200, 400);
    }
    public KmDto main() {
        return new KmDto(initKm, finalKm);
    }
}

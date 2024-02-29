package api.lineCheck.data;

import api.lineCheck.core.dtos.ManufactureDto;
import api.lineCheck.data.usecase.ManufactureService;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.infra.repositories.JPAManufacture;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ManufactureServiceTest {
    @InjectMocks
    public ManufactureService sut;
    @Mock
    public JPAManufacture repository;
    @Test
    public void should_create_new_manufacture() {
        Faker faker = new Faker();
        String manufactureName = faker.company().name();
        Manufacture manufacture = Manufacture.create(manufactureName);
        ManufactureDto dto = new ManufactureDto(manufactureName);
        sut.create(dto);
        verify(repository, times(1)).create(manufacture);
    }
}

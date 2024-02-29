package api.lineCheck.data;

import api.lineCheck.core.dtos.DriverServiceDto;
import api.lineCheck.data.usecase.DriverServiceService;
import api.lineCheck.domain.service.DriverService;
import api.lineCheck.infra.repositories.JPADriverService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DriverServiceServiceTest {
    @InjectMocks
    public DriverServiceService sut;
    @Mock
    public JPADriverService repository;
    @Test
    public void should_create_new_service() {
        Faker faker = new Faker();
        String service = faker.harryPotter().spell();
        DriverService driverService = DriverService.create(service);
        DriverServiceDto dto = new DriverServiceDto(service);
        sut.create(dto);
        verify(repository, times(1)).create(driverService);
    }
}

package api.lineCheck.infra;

import api.lineCheck.domain.service.DriverService;
import api.lineCheck.infra.interfaces.JPAs.DriverServiceJPArepositories;
import api.lineCheck.infra.repositories.JPADriverService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JPADriverServiceTest {
    @InjectMocks
    public JPADriverService sut;
    @Mock
    public DriverServiceJPArepositories dbMock;
    @Test
    public void should_save_new_driver_service_in_db() {
        Faker faker = new Faker();
        String service = faker.harryPotter().spell();
        DriverService driverService = DriverService.create(service);
        sut.create(driverService);
        verify(dbMock, times(1)).save(driverService);
    }
}

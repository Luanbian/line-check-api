package api.lineCheck.data;

import api.lineCheck.core.dtos.VehicleDto;
import api.lineCheck.data.usecase.VehicleService;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.infra.repositories.JPAVehicle;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @InjectMocks
    public VehicleService sut;
    @Mock
    public JPAVehicle repository;
    @Test
    public void should_create_new_vehicle() {
        Faker faker = new Faker();
        String vehicleName = faker.aviation().airport();
        Vehicle vehicle = Vehicle.create(vehicleName);
        VehicleDto dto = new VehicleDto(vehicleName);
        sut.create(dto);
        verify(repository, times(1)).create(vehicle);
    }
}

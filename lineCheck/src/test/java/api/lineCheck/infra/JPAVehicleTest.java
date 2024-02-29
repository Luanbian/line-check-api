package api.lineCheck.infra;

import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.infra.interfaces.JPAs.VehicleJPArepositories;
import api.lineCheck.infra.repositories.JPAVehicle;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JPAVehicleTest {
    @InjectMocks
    public JPAVehicle sut;
    @Mock
    public VehicleJPArepositories db;
    @Test
    public void should_save_new_vehicle_in_db() {
        Faker faker = new Faker();
        String vehicleName = faker.aviation().airport();
        Vehicle vehicle = Vehicle.create(vehicleName);
        sut.create(vehicle);
        verify(db, times(1)).save(vehicle);
    }
}

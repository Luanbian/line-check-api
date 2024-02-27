package api.lineCheck.infra.interfaces.JPAs;

import api.lineCheck.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleJPArepositories extends JpaRepository<Vehicle, UUID> {
}

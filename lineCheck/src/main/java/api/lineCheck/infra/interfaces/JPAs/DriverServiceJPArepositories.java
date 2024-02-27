package api.lineCheck.infra.interfaces.JPAs;

import api.lineCheck.domain.service.DriverService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverServiceJPArepositories extends JpaRepository<DriverService, UUID> {
}

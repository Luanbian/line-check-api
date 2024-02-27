package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.manufacture.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufactureJPArepositories extends JpaRepository<Manufacture, UUID> {
}

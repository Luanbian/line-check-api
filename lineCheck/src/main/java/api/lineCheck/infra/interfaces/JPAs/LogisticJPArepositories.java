package api.lineCheck.infra.interfaces.JPAs;

import api.lineCheck.domain.logistic.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogisticJPArepositories extends JpaRepository<Logistic,UUID> {
}

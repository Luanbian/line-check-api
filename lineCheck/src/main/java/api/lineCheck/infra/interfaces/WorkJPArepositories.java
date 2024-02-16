package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkJPArepositories extends JpaRepository<Work, UUID> {
    @Query("SELECT w.id, ac.name, w.startJourneyModel, w.startLineModel, w.endLineModel, " +
            "se.service, lo.logistic, man.manufacture, ve.vehicle, w.daysOfTheWeek " +
            "FROM Work w INNER JOIN Account ac ON ac.id = w.account.id " +
            "INNER JOIN Service se ON se.id = w.service.id " +
            "INNER JOIN Logistic lo ON lo.id = w.logistic.id " +
            "INNER JOIN Manufacture man ON man.id = w.manufacture.id " +
            "INNER JOIN Vehicle ve ON ve.id = w.vehicle.id")
    List<Object[]> findDriverWorkData();
    @Query("SELECT w.id, ac.name, w.startJourneyModel, w.startJourneyReal, w.startLineModel, " +
            "w.startLineReal, w.endLineModel, w.endLineReal, se.service, " +
            "lo.logistic, man.manufacture, ve.vehicle, w.daysOfTheWeek " +
            "FROM Work w INNER JOIN Account ac ON ac.id = w.account.id " +
            "INNER JOIN Service se ON se.id = w.service.id " +
            "INNER JOIN Logistic lo ON lo.id = w.logistic.id " +
            "INNER JOIN Manufacture man ON man.id = w.manufacture.id " +
            "INNER JOIN Vehicle ve ON ve.id = w.vehicle.id")
    List<Object[]> findManagerWorkData();
    Optional<Work> findById(UUID id);
}

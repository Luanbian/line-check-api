package api.lineCheck.infra.interfaces.JPAs;

import api.lineCheck.domain.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkJPArepositories extends JpaRepository<Work, UUID> {
    @Query("SELECT w.id, ac.name, w.startJourneyModel, w.startLineModel, w.endLineModel, " +
            "se.service, lo.logistic, man.manufacture, ve.vehicle, w.daysOfTheWeek " +
            "FROM Work w INNER JOIN Account ac ON ac.id = w.accountId " +
            "INNER JOIN Service se ON se.id = w.serviceId " +
            "INNER JOIN Logistic lo ON lo.id = w.logisticId " +
            "INNER JOIN Manufacture man ON man.id = w.manufactureId " +
            "INNER JOIN Vehicle ve ON ve.id = w.vehicleId")
    List<Object[]> findDriverWorkData();
    @Query("SELECT w.id, ac.name, w.startJourneyModel, w.startJourneyReal, w.startLineModel, " +
            "w.startLineReal, w.endLineModel, w.endLineReal, se.service, " +
            "lo.logistic, man.manufacture, ve.vehicle, w.daysOfTheWeek " +
            "FROM Work w INNER JOIN Account ac ON ac.id = w.accountId " +
            "INNER JOIN Service se ON se.id = w.serviceId " +
            "INNER JOIN Logistic lo ON lo.id = w.logisticId " +
            "INNER JOIN Manufacture man ON man.id = w.manufactureId " +
            "INNER JOIN Vehicle ve ON ve.id = w.vehicleId")
    List<Object[]> findManagerWorkData();
    @Query("SELECT id, 'accounts' as origin, name, deviceToken FROM Account " +
            "UNION ALL " +
            "SELECT id, 'logistics' AS origin, logistic AS name, '' AS deviceToken FROM Logistic " +
            "UNION ALL " +
            "SELECT id, 'manufactures' AS origin, manufacture AS name, '' AS deviceToken FROM Manufacture " +
            "UNION ALL " +
            "SELECT id, 'services' AS origin, service AS name, '' AS deviceToken FROM Service " +
            "UNION ALL " +
            "SELECT id, 'vehicles' AS origin, vehicle AS name, '' AS deviceToken FROM Vehicle ")
    List<Object[]> findEntityNames();

    Optional<Work> findById(UUID id);
    @Query("SELECT w FROM Work w " +
            "WHERE w.accountId = :accountId " +
            "AND w.startLineModel = :startLine ")
    List<Work> findWorkConflict(UUID accountId, Time startLine);
}

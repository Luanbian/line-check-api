package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.work.Work;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.time.DayOfWeek;
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
    Optional<Work> findById(UUID id);
    @Modifying
    @Query(value = "UPDATE accounts SET work_list_ids = array_append(work_list_ids, ?2) " +
                    "WHERE id = ?1", nativeQuery = true)
    void setWorkListId(UUID accountId, UUID workId);
    @Query("SELECT w FROM Work w " +
            "WHERE w.accountId = :accountId " +
            "AND w.startLineModel = :startLine ")
    List<Work> findWorkConflict(UUID accountId, Time startLine);
}

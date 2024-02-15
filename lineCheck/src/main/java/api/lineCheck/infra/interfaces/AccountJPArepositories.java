package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface AccountJPArepositories extends JpaRepository<Account, UUID> {
    UserDetails findByEmail(String email);
    List<Account> findByPhone(String phone);
    Account findByEmailAndPassword(String email, String password);
    @Query("SELECT ac.name, w.startJourneyModel, w.startLineModel, w.endLineModel," +
            "se.service, lo.logistic, man.manufacture, ve.vehicle, w.daysOfTheWeek " +
            "FROM Work w INNER JOIN Account ac ON ac.id = w.account.id " +
            "INNER JOIN Service se ON se.id = w.service.id " +
            "INNER JOIN Logistic lo ON lo.id = w.logistic.id " +
            "INNER JOIN Manufacture man ON man.id = w.manufacture.id " +
            "INNER JOIN Vehicle ve ON ve.id = w.vehicle.id")
    List<Object[]> findDriverWorkData();
}

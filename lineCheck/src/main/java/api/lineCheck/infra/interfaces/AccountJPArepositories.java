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
    @Query("SELECT a.name, cp.startJourney, cp.startLine, cp.endExpedient, m.startJourney, m.startLine, m.endExpedient, " +
            "s.service, r.road, man.manufacture, v.vehicle, w.monday, w.tuesday, w.wednesday, w.thursday, w.friday, w.saturday, w.sunday " +
            "FROM Account a " +
            "INNER JOIN Checkpoint cp ON cp.account.id = a.id " +
            "INNER JOIN Marker m ON m.account.id = a.id " +
            "INNER JOIN Service s ON s.account.id = a.id " +
            "INNER JOIN Road r ON r.account.id = a.id " +
            "INNER JOIN Vehicle v ON v.account.id = a.id " +
            "INNER JOIN Manufacture man ON man.account.id = a.id " +
            "INNER JOIN Week w ON w.account.id = a.id")
    List<Object[]> findDriverWorkData();
}

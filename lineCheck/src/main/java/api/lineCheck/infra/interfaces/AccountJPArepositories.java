package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountJPArepositories extends JpaRepository<Account, UUID> {
    void saveAccount(Account account);
    List<Account> findByEmail(String email);
    List<Account> findByPhone(String phone);
}

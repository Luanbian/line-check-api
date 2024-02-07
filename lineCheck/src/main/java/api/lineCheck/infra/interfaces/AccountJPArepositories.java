package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface AccountJPArepositories extends JpaRepository<Account, UUID> {
    UserDetails findByEmail(String email);
    List<Account> findByPhone(String phone);
    Account findByEmailAndPassword(String email, String password);
}

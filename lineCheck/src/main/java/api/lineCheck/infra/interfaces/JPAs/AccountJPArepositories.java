package api.lineCheck.infra.interfaces.JPAs;

import api.lineCheck.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountJPArepositories extends JpaRepository<Account, UUID> {
    Optional<Account> findById(UUID id);
    UserDetails findByEmail(String email);
    List<Account> findByPhone(String phone);
    Account findByEmailAndPassword(String email, String password);
}

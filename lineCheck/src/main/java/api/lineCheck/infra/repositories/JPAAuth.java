package api.lineCheck.infra.repositories;

import api.lineCheck.domain.account.Account;
import api.lineCheck.infra.interfaces.JPAs.AccountJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IAuthRepository;
import api.lineCheck.presentation.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPAAuth implements IAuthRepository {
    private final AccountJPArepositories JPARepository;
    @Autowired
    public JPAAuth(AccountJPArepositories JPARepository) {
        this.JPARepository = JPARepository;
    }
    @Override
    public Account authByCredentials(String email, String password) {
        Account account = this.JPARepository.findByEmailAndPassword(email, password);
        if (account == null) {
            throw new InvalidCredentialsException();
        }
        return account;
    }
}

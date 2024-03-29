package api.lineCheck.infra.repositories;

import api.lineCheck.domain.account.Account;
import api.lineCheck.infra.interfaces.JPAs.AccountJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IAccountRepository;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAAccount implements IAccountRepository {
    private final AccountJPArepositories db;
    @Autowired
    public JPAAccount(AccountJPArepositories db) {
        this.db = db;
    }
    @Override
    public void create(Account data) {
        this.EmailAlreadyExists(data.getEmail());
        this.PhoneAlreadyExists(data.getPhone());
        db.save(data);
    }
    private void EmailAlreadyExists(String email) {
        UserDetails accounts = db.findByEmail(email);
        if (accounts != null) {
            throw new EmailAlreadyExistsException();
        }
    }
    private void PhoneAlreadyExists(String phone) {
        List<Account> accounts = db.findByPhone(phone);
        if (!accounts.isEmpty()) {
            throw new PhoneAlreadyExistsException();
        }
    }
}

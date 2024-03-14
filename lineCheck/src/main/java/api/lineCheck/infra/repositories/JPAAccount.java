package api.lineCheck.infra.repositories;

import api.lineCheck.domain.account.Account;
import api.lineCheck.infra.interfaces.JPAs.AccountJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IAccountRepository;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.NotFoundAccountException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Override
    public void insertDeviceToken(String accountId, String deviceToken) {
        Account account = this.findAccountById(accountId);
        account.setDeviceToken(deviceToken);
        db.save(account);
    }
    private Account findAccountById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Account> optionalAccount = db.findById(uuid);
        if(optionalAccount.isEmpty()) {
            throw new NotFoundAccountException();
        }
        return optionalAccount.get();
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

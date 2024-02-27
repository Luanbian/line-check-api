package api.lineCheck.infra.repositories;

import api.lineCheck.domain.account.Account;
import api.lineCheck.infra.interfaces.JPAs.AccountJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAAccount implements IRepository<Account> {
    private final AccountJPArepositories JPARepository;
    @Autowired
    public JPAAccount(AccountJPArepositories JPARepository) {
        this.JPARepository = JPARepository;
    }
    @Override
    public void create(Account data) {
        this.EmailAlreadyExists(data.getEmail());
        this.PhoneAlreadyExists(data.getPhone());
        JPARepository.save(data);
    }
    private void EmailAlreadyExists(String email) {
        UserDetails accounts = JPARepository.findByEmail(email);
        if (accounts != null) {
            throw new EmailAlreadyExistsException();
        }
    }
    private void PhoneAlreadyExists(String phone) {
        List<Account> accounts = JPARepository.findByPhone(phone);
        if (!accounts.isEmpty()) {
            throw new PhoneAlreadyExistsException();
        }
    }
}

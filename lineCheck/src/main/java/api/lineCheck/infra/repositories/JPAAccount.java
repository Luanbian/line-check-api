package api.lineCheck.infra.repositories;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.IRepository;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
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
        boolean emailAlreadyExists = this.EmailAlreadyExists(data.getEmail());
        if (emailAlreadyExists) {
            throw new EmailAlreadyExistsException();
        }
        boolean phoneAlreadyExists = this.PhoneAlreadyExists(data.getPhone());
        if (phoneAlreadyExists) {
            throw new PhoneAlreadyExistsException();
        }
        JPARepository.saveAccount(data);
    }
    private boolean EmailAlreadyExists(String email) {
        List<Account> accounts = JPARepository.findByEmail(email);
        return !accounts.isEmpty();
    }
    private boolean PhoneAlreadyExists(String phone) {
        List<Account> accounts = JPARepository.findByPhone(phone);
        return accounts.isEmpty();
    }
}

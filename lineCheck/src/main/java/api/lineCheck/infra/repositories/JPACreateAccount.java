package api.lineCheck.infra.repositories;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.ICreateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JPACreateAccount implements ICreateRepository<Account> {
    private final AccountJPArepositories JPARepository;
    @Autowired
    public JPACreateAccount(AccountJPArepositories JPARepository) {
        this.JPARepository = JPARepository;
    }
    @Override
    public void create(Account data) {
        JPARepository.saveAccount(data);
    }
}

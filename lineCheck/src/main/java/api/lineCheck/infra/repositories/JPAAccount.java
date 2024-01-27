package api.lineCheck.infra.repositories;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.interfaces.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPAAccount implements IRepository<Account> {
    private final AccountJPArepositories JPARepository;
    @Autowired
    public JPAAccount(AccountJPArepositories JPARepository) {
        this.JPARepository = JPARepository;
    }
    @Override
    public void create(Account data) {
        JPARepository.saveAccount(data);
    }
}

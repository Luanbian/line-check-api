package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.interfaces.IAccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.domain.AccountProps;
import api.lineCheck.infra.repositories.JPAAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private final JPAAccount repository;

    @Autowired
    public AccountService(JPAAccount repository) {
        this.repository = repository;
    }
    @Override
    public Account register(AccountDto data) {
        AccountProps props = new AccountProps(
                data.name(), data.email(), data.phone(), data.password()
        );
        Account account = Account.create(props);
        repository.create(account);
        return account;
    }
}

package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.interfaces.IAccountService;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.account.AccountProps;
import api.lineCheck.domain.account.Role;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.presentation.exceptions.InvalidRoleException;
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
        Role role = switch (data.role().toUpperCase()) {
            case "MANAGER" -> Role.MANAGER;
            case "DRIVER" -> Role.DRIVER;
            default -> throw new InvalidRoleException();
        };
        AccountProps props = new AccountProps(
                data.name(), data.email(), data.phone(), data.password(), role
        );
        Account account = Account.create(props);
        repository.create(account);
        return account;
    }
}

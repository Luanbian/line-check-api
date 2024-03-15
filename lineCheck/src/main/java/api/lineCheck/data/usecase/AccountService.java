package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.core.dtos.DeviceTokenDto;
import api.lineCheck.data.interfaces.IAccountService;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.account.AccountProps;
import api.lineCheck.domain.account.Role;
import api.lineCheck.infra.interfaces.persistances.IAccountRepository;
import api.lineCheck.presentation.exceptions.InvalidRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository repository;
    @Autowired
    public AccountService(IAccountRepository repository) {
        this.repository = repository;
    }
    @Override
    public Account register(AccountDto dto) {
        AccountProps props = convertDtoToProps(dto);
        Account account = Account.create(props);
        repository.create(account);
        return account;
    }
    private AccountProps convertDtoToProps (AccountDto dto) {
        Role role = switch (dto.role().toUpperCase()) {
            case "MANAGER" -> Role.MANAGER;
            case "DRIVER" -> Role.DRIVER;
            default -> throw new InvalidRoleException();
        };
        return new AccountProps(
                dto.name(), dto.email(), dto.phone(), dto.password(), role
        );
    }
}

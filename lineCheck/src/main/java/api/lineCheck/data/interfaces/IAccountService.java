package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.domain.Account;

public interface IAccountService {
    Account register(AccountDto data);
}

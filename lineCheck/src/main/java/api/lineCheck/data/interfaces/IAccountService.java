package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.domain.account.Account;

public interface IAccountService {
    Account register(AccountDto data);
}

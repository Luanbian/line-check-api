package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.account.Account;

public interface IAuthRepository {
    Account authByCredentials(String email, String password);
}

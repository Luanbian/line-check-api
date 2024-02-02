package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.Account;

public interface IAuthRepository {
    Account authByCredentials(String email, String password);
}

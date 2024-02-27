package api.lineCheck.infra.interfaces.persistances;

import api.lineCheck.domain.account.Account;

public interface ITokenService {
    String generate(Account account);
    String verify(String token) throws Exception;
}

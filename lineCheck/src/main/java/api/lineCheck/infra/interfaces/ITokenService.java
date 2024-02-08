package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.account.Account;

public interface ITokenService {
    String generate(Account account);
    String verify(String token) throws Exception;
}

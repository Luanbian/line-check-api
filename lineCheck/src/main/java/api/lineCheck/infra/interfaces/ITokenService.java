package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.Account;

public interface ITokenService {
    String generate(Account account);
}

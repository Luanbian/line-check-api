package api.lineCheck.infra.interfaces.persistances;

import api.lineCheck.domain.account.Account;

public interface IAccountRepository {
    void create(Account data);
    void insertDeviceToken(String accountId, String deviceToken);
}

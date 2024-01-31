package api.lineCheck.data.usecase;

import api.lineCheck.data.interfaces.IAuthService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.IAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final IAuthRepository repository;
    @Autowired
    public AuthService(IAuthRepository repository) {
        this.repository = repository;
    }
    @Override
    public String login(String email, String password) {
        Account account = repository.authByCredentials(email, password);
        return account.toString();
    }
}

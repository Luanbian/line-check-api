package api.lineCheck.data.usecase;

import api.lineCheck.data.interfaces.IAuthService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.IAuthRepository;
import api.lineCheck.infra.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final IAuthRepository repository;
    private final ITokenService token;
    @Autowired
    public AuthService(IAuthRepository repository, ITokenService token) {
        this.repository = repository;
        this.token = token;
    }
    @Override
    public String login(String email, String password) {
        Account account = repository.authByCredentials(email, password);
        return token.generate(account);
    }
}

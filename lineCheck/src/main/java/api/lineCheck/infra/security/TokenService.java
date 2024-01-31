package api.lineCheck.infra.security;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.ITokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generate(Account account) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token;
        token = JWT
                .create()
                .withSubject(account.toString())
                .sign(algorithm);
        return token;
    }
}

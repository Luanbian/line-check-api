package api.lineCheck.infra.security;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.ITokenService;
import api.lineCheck.presentation.exceptions.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
                .withIssuer("auth-api")
                .withSubject(account.getEmail())
                .sign(algorithm);
        return token;
    }
    @Override
    public String verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException();
        }
    }
}

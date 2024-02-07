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
        List<String> roles = Collections.singletonList(String.valueOf(account.getRole()));
        String token;
        token = JWT
                .create()
                .withClaim("userRole", roles)
                .withSubject(account.toString())
                .sign(algorithm);
        return token;
    }
    @Override
    public String verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decoded = verifier.verify(token);
            List<String> userRoles = decoded.getClaim("userRole").asList(String.class);
            String userRole;
            userRole = userRoles.get(0);
            return userRole;
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException();
        }
    }
}

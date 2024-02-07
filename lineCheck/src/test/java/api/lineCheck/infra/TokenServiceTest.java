package api.lineCheck.infra;

import api.lineCheck.domain.Account;
import api.lineCheck.domain.Role;
import api.lineCheck.infra.security.TokenService;
import api.lineCheck.mocks.AccountPropsMock;
import api.lineCheck.presentation.exceptions.InvalidTokenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @InjectMocks
    public TokenService sut;
    public AccountPropsMock propsMock = new AccountPropsMock();
    @Test
    public void should_return_random_token_if_success() {
        Account account = Account.create(propsMock.main());
        ReflectionTestUtils.setField(sut, "secret", "secret_test");
        String token = sut.generate(account);
        assertNotNull(token);
    }
    @Test
    public void should_return_email_of_logged_user_if_success() {
        Account account = Account.create(propsMock.main());
        ReflectionTestUtils.setField(sut, "secret", "secret_test");
        String token = sut.generate(account);
        String email = sut.verify(token);
        assertEquals(email, account.getEmail());
    }
    @Test
    public void should_throw_InvalidTokenException_if_token_was_invalid() {
        ReflectionTestUtils.setField(sut, "secret", "secret_test");
        String invalidToken = "invalid_token";
        assertThrows(InvalidTokenException.class, () -> sut.verify(invalidToken));
    }
}

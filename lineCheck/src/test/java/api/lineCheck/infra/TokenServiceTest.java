package api.lineCheck.infra;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.security.TokenService;
import api.lineCheck.mocks.AccountPropsMock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
}

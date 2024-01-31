package api.lineCheck.data;

import api.lineCheck.data.usecase.AuthService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.repositories.JPAAuth;
import api.lineCheck.mocks.AccountPropsMock;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.presentation.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    public AuthService sut;
    @Mock
    public JPAAuth repository;
    public AccountPropsMock propsMock = new AccountPropsMock();
    @Test
    public void should_return_token_if_user_found_with_success() {
        String email = propsMock.email;
        String password = propsMock.password;
        when(repository.authByCredentials(email, password)).thenReturn(Account.create(propsMock.main()));
        String token = sut.login(email, password);
        assertEquals(token, Account.create(propsMock.main()).toString());
    }
    @Test
    public void should_throw_if_repository_not_found_account() {
        String email = propsMock.email;
        String password = propsMock.password;
        when(repository.authByCredentials(email, password)).thenThrow(new InvalidCredentialsException());
        assertThrows(InvalidCredentialsException.class, () -> sut.login(email, password));
    }
}

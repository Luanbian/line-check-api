package api.lineCheck.infra;

import api.lineCheck.domain.Account;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.repositories.JPAAuth;
import api.lineCheck.mocks.AccountPropsMock;
import static org.junit.jupiter.api.Assertions.*;

import api.lineCheck.presentation.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class JPAAuthTest {
    @InjectMocks
    public JPAAuth sut;
    @Mock
    public AccountJPArepositories JPARepository;
    public AccountPropsMock propsMock = new AccountPropsMock();
    @Test
    public void should_find_account_by_credentials() {
        Account foundAccount = Account.create(propsMock.main());
        String email = propsMock.email;
        String password = propsMock.password;
        when(JPARepository.findByEmailAndPassword(email, password))
                .thenReturn(foundAccount);
        Account account = sut.authByCredentials(email, password);
        assertNotNull(account);
        assertEquals(account, foundAccount);
    }
    @Test
    public void should_throw_invalid_credentials_exception_if_repository_return_null() {
        when(JPARepository.findByEmailAndPassword(propsMock.email, propsMock.password)).thenReturn(null);
        assertThrows(InvalidCredentialsException.class, () -> sut.authByCredentials(propsMock.email, propsMock.password));
    }
}

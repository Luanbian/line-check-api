package api.lineCheck.infra;

import api.lineCheck.domain.Account;
import api.lineCheck.domain.AccountProps;
import api.lineCheck.infra.interfaces.AccountJPArepositories;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.mocks.AccountPropsMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JPAAccountTest {
    @InjectMocks
    public JPAAccount sut;
    @Mock
    public AccountJPArepositories JPARepository;
    public AccountPropsMock propsMock = new AccountPropsMock();
    @Test
    public void should_be_able_to_save_account_in_database() {
        Account account = Account.create(propsMock.main());
        sut.create(account);
        verify(JPARepository, times(1)).saveAccount(account);
    }
}

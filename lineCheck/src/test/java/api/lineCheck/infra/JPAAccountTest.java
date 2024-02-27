package api.lineCheck.infra;

import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.account.AccountProps;
import api.lineCheck.infra.interfaces.JPAs.AccountJPArepositories;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.mocks.AccountPropsMock;
import api.lineCheck.presentation.exceptions.EmailAlreadyExistsException;
import api.lineCheck.presentation.exceptions.PhoneAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
        verify(JPARepository, times(1)).save(account);
    }
    @Test
    public void should_throw_Email_Already_exists_exception() {
        String existingEmail = "existing@email.com";
        AccountProps props = propsMock.main();
        props = new AccountProps(props.name(), existingEmail, props.phone(), props.password(), props.role());
        Account data = Account.create(props);
        when(JPARepository.findByEmail(existingEmail))
                .thenReturn(Account.create(props));
        assertThrows(EmailAlreadyExistsException.class, () -> sut.create(data));
        verify(JPARepository, never()).save(data);
    }
    @Test
    public void should_throw_Phone_Already_exists_exception() {
        String existingPhone = "15999999999";
        AccountProps props = propsMock.main();
        props = new AccountProps(props.name(), props.email(), existingPhone, props.password(), props.role());
        Account data = Account.create(props);
        when(JPARepository.findByPhone(existingPhone))
                .thenReturn(Collections.singletonList(Account.create(props)));
        assertThrows(PhoneAlreadyExistsException.class, () -> sut.create(data));
        verify(JPARepository, never()).save(data);
    }
}

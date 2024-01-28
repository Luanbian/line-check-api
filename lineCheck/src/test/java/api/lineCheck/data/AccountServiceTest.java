package api.lineCheck.data;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.usecase.AccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.repositories.JPAAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    public AccountService sut;
    @Mock
    public JPAAccount repository;

    @Test
    public void should_be_able_to_register_account_with_correct_params() {
        AccountDto dto = new AccountDto(
                "fake_name",
                "fake.user@email.com",
                "159191919",
                "1234"
        );
        Account result = sut.register(dto);
        assertNotNull(result);
        assertEquals("fake_name", result.getName());
        assertEquals("fake.user@email.com", result.getEmail());
        assertEquals("159191919", result.getPhone());
        assertEquals("1234", result.getPassword());
    }
}

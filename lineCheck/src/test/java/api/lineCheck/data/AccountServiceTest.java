package api.lineCheck.data;

import api.lineCheck.core.dtos.AccountDto;
import api.lineCheck.data.usecase.AccountService;
import api.lineCheck.domain.Account;
import api.lineCheck.infra.repositories.JPAAccount;
import api.lineCheck.mocks.AccountDtoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    public AccountService sut;
    @Mock
    public JPAAccount repository;

    @Test
    public void should_be_able_to_register_account_with_correct_params() {
        AccountDtoMock dtoMock = new AccountDtoMock();
        AccountDto dto = dtoMock.main();
        Account result = sut.register(dto);
        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertEquals(dto.password(), result.getPassword());
    }
    @Test void should_call_repository_with_correct_values () {
        AccountDtoMock dtoMock = new AccountDtoMock();
        Account result = sut.register(dtoMock.main());
        verify(repository, times(1)).create(result);
    }
}

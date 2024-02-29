package api.lineCheck.data;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.data.usecase.LogisticService;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.repositories.JPALogistic;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogisticServiceTest {
    @InjectMocks
    public LogisticService sut;
    @Mock
    public JPALogistic repository;
    @Test
    public void should_create_new_logistic() {
        Faker faker = new Faker();
        String road = faker.lordOfTheRings().location();
        Logistic logistic = Logistic.create(road);
        LogisticDto dto = new LogisticDto(road);
        sut.create(dto);
        verify(repository,times(1)).create(logistic);
    }
}

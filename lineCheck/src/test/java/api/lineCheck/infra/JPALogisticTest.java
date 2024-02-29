package api.lineCheck.infra;

import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.interfaces.JPAs.LogisticJPArepositories;
import api.lineCheck.infra.repositories.JPALogistic;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JPALogisticTest {
    @InjectMocks
    public JPALogistic sut;
    @Mock
    public LogisticJPArepositories db;
    @Test
    public void should_save_new_logistic_in_db() {
        Faker faker = new Faker();
        String logisticName = faker.lordOfTheRings().location();
        Logistic logistic = Logistic.create(logisticName);
        sut.create(logistic);
        verify(db, times(1)).save(logistic);
    }
}

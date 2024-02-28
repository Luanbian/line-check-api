package api.lineCheck.infra;

import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.infra.interfaces.JPAs.ManufactureJPArepositories;
import api.lineCheck.infra.repositories.JPAManufacture;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JPAManufactureTest {
    @InjectMocks
    public JPAManufacture sut;
    @Mock
    public ManufactureJPArepositories dbMock;
    @Test
    public void should_save_new_manufacture_in_db() {
        Faker faker = new Faker();
        String manufactureName = faker.company().name();
        Manufacture manufacture = Manufacture.create(manufactureName);
        sut.create(manufacture);
        verify(dbMock, times(1)).save(manufacture);
    }
}

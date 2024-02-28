package api.lineCheck.presentation;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.data.usecase.LogisticService;
import api.lineCheck.presentation.controllers.LogisticController;
import com.github.javafaker.Faker;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class LogisticControllerTest {
    @InjectMocks
    public LogisticController sut;
    @Mock
    public LogisticService service;
    @Test
    public void should_return_ok_if_create_new_logistic_with_success() {
        Faker faker = new Faker();
        String logistic = faker.lordOfTheRings().location();
        LogisticDto dto = new LogisticDto(logistic);
        ResponseEntity response = sut.create(dto);
        verify(service, times(1)).create(dto);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void should_fall_in_catch_if_service_throws() {
        Faker faker = new Faker();
        String logistic = faker.lordOfTheRings().location();
        LogisticDto dto = new LogisticDto(logistic);
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).create(dto);
        ResponseEntity response = sut.create(dto);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
}

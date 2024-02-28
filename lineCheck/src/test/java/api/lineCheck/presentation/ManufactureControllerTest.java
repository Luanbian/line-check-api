package api.lineCheck.presentation;

import api.lineCheck.core.dtos.ManufactureDto;
import api.lineCheck.data.usecase.ManufactureService;
import api.lineCheck.presentation.controllers.ManufactureController;
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
public class ManufactureControllerTest {
    @InjectMocks
    public ManufactureController sut;
    @Mock
    public ManufactureService service;
    @Test
    public void should_return_ok_if_create_new_manufacture_with_success() {
        Faker faker = new Faker();
        String manufactureName = faker.company().name();
        ManufactureDto dto = new ManufactureDto(manufactureName);
        ResponseEntity response = sut.create(dto);
        verify(service, times(1)).create(dto);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void should_fall_in_catch_if_service_throws() {
        Faker faker = new Faker();
        String manufactureName = faker.company().name();
        ManufactureDto dto = new ManufactureDto(manufactureName);
        doAnswer(invocation -> {
            throw new Exception();
        }).when(service).create(dto);
        ResponseEntity response = sut.create(dto);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(), "Erro interno do servidor");
    }
}

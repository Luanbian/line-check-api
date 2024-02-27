package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.DriverServiceDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class DriverServiceController {
    private final IService<DriverService, DriverServiceDto> service;
    @Autowired
    public DriverServiceController(IService<DriverService, DriverServiceDto> service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody DriverServiceDto dto) {
        try {
            DriverService driverService = service.create(dto);
            return ResponseEntity.ok(driverService);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.VehicleDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final IService<Vehicle, VehicleDto> service;
    @Autowired
    public VehicleController(IService<Vehicle, VehicleDto> service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody VehicleDto dto) {
        try {
            Vehicle vehicle = service.create(dto);
            return ResponseEntity.ok(vehicle);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

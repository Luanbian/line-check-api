package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.ManufactureDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.manufacture.Manufacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/manufacture")
public class ManufactureController {
    private final IService<Manufacture, ManufactureDto> service;
    @Autowired
    public ManufactureController(IService<Manufacture, ManufactureDto> service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody ManufactureDto dto) {
        try {
            Manufacture manufacture = service.create(dto);
            return ResponseEntity.ok(manufacture);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

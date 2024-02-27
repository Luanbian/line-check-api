package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.logistic.Logistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logistic")
public class LogisticController {
    private final IService<Logistic, LogisticDto> service;
    @Autowired
    public LogisticController(IService<Logistic, LogisticDto> service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody LogisticDto dto) {
        try {
            Logistic logistic = service.create(dto);
            return ResponseEntity.ok(logistic);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.data.interfaces.ILogisticService;
import api.lineCheck.domain.logistic.Logistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logistic")
public class LogisticController {
    private final ILogisticService service;
    @Autowired
    public LogisticController(ILogisticService service) {
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

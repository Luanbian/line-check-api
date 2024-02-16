package api.lineCheck.presentation.controllers;

import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/checkpoint")
public class CheckpointController {
    private final IWorkService service;
    @Autowired
    public CheckpointController(IWorkService service) {
        this.service = service;
    }
    @GetMapping("/driver")
    public ResponseEntity driverInfo() {
        try {
            List<WorkDriver> works = service.listWorks();
            if (works.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(works);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
    @GetMapping("/manager")
    public ResponseEntity managerInfo() {
        try {
            List<WorkManager> works = service.listManagerWorks();
            if(works.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(works);
        }catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

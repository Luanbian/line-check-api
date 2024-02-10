package api.lineCheck.presentation.controllers;

import api.lineCheck.data.interfaces.IWorkService;
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
        List<Object[]> works = service.listWorks();
        return ResponseEntity.ok(works);
    }
    @GetMapping("/manager")
    public ResponseEntity managerInfo() {
        return ResponseEntity.ok("Manager screen");
    }
}

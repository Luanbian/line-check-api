package api.lineCheck.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkpoint")
public class CheckpointController {
    @GetMapping("/driver")
    public ResponseEntity driverInfo() {
        return ResponseEntity.ok("Driver screen");
    }
    @GetMapping("/manager")
    public ResponseEntity managerInfo() {
        return ResponseEntity.ok("Manager screen");
    }
}

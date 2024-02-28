package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.work.Work;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.LineConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/driver")
    public ResponseEntity driverUpdateLineCheck(
            @RequestParam(value = "workId") String workId,
            @RequestParam(value = "accountId") String accountId,
            @RequestParam(value = "marker") String marker
    ) {
        try {
            service.updateDriverLineChecks(workId, accountId, marker);
            return ResponseEntity.ok().build();
        } catch (ActionNotPermittedException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
    @PostMapping("/line")
    public ResponseEntity createLine(@Validated @RequestBody WorkDto dto) {
        try {
            Work work = service.create(dto);
            return ResponseEntity.ok(work);
        } catch (LineConflictException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

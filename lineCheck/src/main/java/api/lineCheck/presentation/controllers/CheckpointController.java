package api.lineCheck.presentation.controllers;

import api.lineCheck.core.dtos.KmDto;
import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.data.utils.entities.EntityNames;
import api.lineCheck.domain.work.Work;
import api.lineCheck.data.utils.entities.WorkDriver;
import api.lineCheck.data.utils.entities.WorkManager;
import api.lineCheck.presentation.exceptions.ActionNotPermittedException;
import api.lineCheck.presentation.exceptions.LineConflictException;
import api.lineCheck.presentation.exceptions.NotFoundWorkException;
import api.lineCheck.presentation.helpers.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            List<EntityNames> entityNames = service.listEntityNames();
            ResponseBody response = ResponseBody.create(works, entityNames);
            return ResponseEntity.ok().body(response);
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
    @PutMapping("/line")
    public ResponseEntity updateLine(@RequestParam("workId") String workId, @Validated @RequestBody WorkDto dto) {
        try {
            Work work = service.update(workId, dto);
            return ResponseEntity.ok(work);
        } catch (LineConflictException | NotFoundWorkException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
    @PostMapping("/km")
    public ResponseEntity insertKm(
            @RequestParam(value = "workId") String workId,
            @RequestParam(value = "accountId") String accountId,
            @Validated @RequestBody KmDto dto) {
        try {
            service.insertKm(workId,accountId, dto);
            return ResponseEntity.ok().build();
        } catch (NotFoundWorkException | ActionNotPermittedException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro interno do servidor");
        }
    }
}

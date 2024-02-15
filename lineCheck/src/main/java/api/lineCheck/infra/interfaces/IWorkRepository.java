package api.lineCheck.infra.interfaces;

import api.lineCheck.domain.work.WorkDriver;

import java.util.List;

public interface IWorkRepository {
    List<WorkDriver> list();
}

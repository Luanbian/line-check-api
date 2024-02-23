package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;

import java.util.List;

public interface IWorkService {
    void create(WorkDto dto);
    List<WorkDriver> listWorks();
    List<WorkManager> listManagerWorks();
    void updateDriverLineChecks(String workId, String accountId, String marker);
}

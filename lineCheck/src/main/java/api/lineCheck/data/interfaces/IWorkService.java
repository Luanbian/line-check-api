package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.WorkDto;
import api.lineCheck.data.utils.entities.EntityNames;
import api.lineCheck.domain.work.Work;
import api.lineCheck.data.utils.entities.WorkDriver;
import api.lineCheck.data.utils.entities.WorkManager;

import java.util.List;

public interface IWorkService {
    Work create(WorkDto dto);
    Work update(String id, WorkDto dto);
    List<WorkDriver> listWorks();
    List<WorkManager> listManagerWorks();
    List<EntityNames> listEntityNames();
    void updateDriverLineChecks(String workId, String accountId, String marker);
}

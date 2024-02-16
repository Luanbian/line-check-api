package api.lineCheck.data.interfaces;

import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.domain.work.WorkManager;

import java.util.List;

public interface IWorkService {
    List<WorkDriver> listWorks();
    List<WorkManager> listManagerWorks();
    void updateDriverLineChecks(String workId, String accountId);
}

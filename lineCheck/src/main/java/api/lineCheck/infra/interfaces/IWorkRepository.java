package api.lineCheck.infra.interfaces;

import api.lineCheck.data.enums.LineChecks;
import api.lineCheck.domain.work.Work;

import java.util.List;

public interface IWorkRepository {
    List<Object[]> list();
    List<Object[]> listManager();
    void updateDriverLineChecks(String workId, String accountId, LineChecks lineCheck);
    void create (Work data);
}

package api.lineCheck.infra.interfaces;

import java.util.List;

public interface IWorkRepository {
    List<Object[]> list();
    List<Object[]> listManager();
    void updateStartJourneyReal(String workId, String accountId);
}

package api.lineCheck.presentation.helpers;

import api.lineCheck.data.utils.entities.EntityNames;
import api.lineCheck.data.utils.entities.WorkManager;
import lombok.Data;

import java.util.List;
@Data
public class ResponseBody {
    private List<WorkManager> works;
    private List<EntityNames> entities;
    private ResponseBody(List<WorkManager> workManagers, List<EntityNames> entityNames) {
        this.entities = entityNames;
        this.works = workManagers;
    }
    public static ResponseBody create(List<WorkManager> workManagers, List<EntityNames> entityNames) {
        return new ResponseBody(workManagers, entityNames);
    }
}

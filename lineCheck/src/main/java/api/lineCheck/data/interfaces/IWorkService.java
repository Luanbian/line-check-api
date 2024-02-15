package api.lineCheck.data.interfaces;

import api.lineCheck.domain.work.WorkDriver;

import java.util.List;

public interface IWorkService {
    List<WorkDriver> listWorks();
}

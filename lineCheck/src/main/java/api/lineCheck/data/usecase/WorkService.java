package api.lineCheck.data.usecase;

import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.week.DaysOfTheWeek;
import api.lineCheck.domain.work.WorkDriver;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkService implements IWorkService {
    private final IWorkRepository repository;
    @Autowired
    public WorkService(IWorkRepository repository) {
        this.repository = repository;
    }
    public List<WorkDriver> listWorks() {
        List<Object[]> dbResponse = repository.list();
        return dbResponse.stream().map(this::mapToWorkDriver).collect(Collectors.toList());
    }
    private WorkDriver mapToWorkDriver(Object[] item) {
        return new WorkDriver(
                (UUID) item[0],
                (String) item[1],
                (Time) item[2],
                (Time) item[3],
                (Time) item[4],
                (String) item[5],
                (String) item[6],
                (String) item[7],
                (String) item[8],
                (List<DaysOfTheWeek>) item[9]
        );
    }
}

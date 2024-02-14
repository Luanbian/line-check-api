package api.lineCheck.data.usecase;

import api.lineCheck.data.interfaces.IWorkService;
import api.lineCheck.domain.work.Work;
import api.lineCheck.infra.interfaces.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService implements IWorkService {
    private final IWorkRepository repository;
    @Autowired
    public WorkService(IWorkRepository repository) {
        this.repository = repository;
    }
    public List<Work> listWorks() {
        List<Object[]> dbResponse = repository.list();
        return Work.mapper(dbResponse);
    }
}
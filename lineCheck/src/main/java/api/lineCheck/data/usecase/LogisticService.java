package api.lineCheck.data.usecase;

import api.lineCheck.data.interfaces.ILogisticService;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.interfaces.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LogisticService implements ILogisticService {
    private final IRepository<Logistic> repository;
    @Autowired
    public LogisticService(IRepository<Logistic> repository) {
        this.repository = repository;
    }
    @Override
    public Logistic create(String data) {
        var logistic = Logistic.create(data);
        repository.create(logistic);
        return logistic;
    }
}

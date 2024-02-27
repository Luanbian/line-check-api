package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticService implements IService<Logistic, LogisticDto> {
    private final IRepository<Logistic> repository;
    @Autowired
    public LogisticService(IRepository<Logistic> repository) {
        this.repository = repository;
    }
    @Override
    public Logistic create(LogisticDto dto) {
        var logistic = Logistic.create(dto.logistic());
        repository.create(logistic);
        return logistic;
    }
}

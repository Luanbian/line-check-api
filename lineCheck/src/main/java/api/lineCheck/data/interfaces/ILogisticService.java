package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.domain.logistic.Logistic;

public interface ILogisticService {
    Logistic create(LogisticDto dto);
}

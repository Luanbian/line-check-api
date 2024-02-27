package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.LogisticDto;
import api.lineCheck.domain.logistic.Logistic;

public interface IService<T, Dto> {
    T create(Dto dto);
}

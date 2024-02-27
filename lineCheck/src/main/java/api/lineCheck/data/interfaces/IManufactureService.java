package api.lineCheck.data.interfaces;

import api.lineCheck.core.dtos.ManufactureDto;
import api.lineCheck.domain.manufacture.Manufacture;

public interface IManufactureService {
    Manufacture create(ManufactureDto dto);
}

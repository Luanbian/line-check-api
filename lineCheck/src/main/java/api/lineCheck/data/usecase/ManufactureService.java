package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.ManufactureDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.infra.interfaces.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureService implements IService<Manufacture, ManufactureDto> {
    private final IRepository<Manufacture> repository;
    @Autowired
    public ManufactureService(IRepository<Manufacture> repository) {
        this.repository = repository;
    }
    @Override
    public Manufacture create(ManufactureDto dto) {
        var manufacture = Manufacture.create(dto.manufacture());
        repository.create(manufacture);
        return manufacture;
    }
}

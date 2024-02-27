package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.DriverServiceDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.service.DriverService;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceService implements IService<DriverService, DriverServiceDto> {
    private final IRepository<DriverService> repository;
    @Autowired
    public DriverServiceService(IRepository<DriverService> repository) {
        this.repository = repository;
    }
    @Override
    public DriverService create(DriverServiceDto driverServiceDto) {
        var driverService = DriverService.create(driverServiceDto.service());
        repository.create(driverService);
        return driverService;
    }
}

package api.lineCheck.data.usecase;

import api.lineCheck.core.dtos.VehicleDto;
import api.lineCheck.data.interfaces.IService;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IService<Vehicle, VehicleDto> {
    private final IRepository<Vehicle> repository;
    @Autowired
    public VehicleService (IRepository<Vehicle> repository) {
        this.repository = repository;
    }
    @Override
    public Vehicle create(VehicleDto vehicleDto) {
        var vehicle = Vehicle.create(vehicleDto.vehicle());
        repository.create(vehicle);
        return vehicle;
    }
}

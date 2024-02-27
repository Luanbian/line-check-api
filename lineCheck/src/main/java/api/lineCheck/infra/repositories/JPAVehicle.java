package api.lineCheck.infra.repositories;

import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.infra.interfaces.JPAs.VehicleJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPAVehicle implements IRepository<Vehicle> {
    private final VehicleJPArepositories db;
    @Autowired
    public JPAVehicle(VehicleJPArepositories db) {
        this.db = db;
    }
    @Override
    public void create(Vehicle data) {
        db.save(data);
    }
}

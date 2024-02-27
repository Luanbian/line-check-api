package api.lineCheck.infra.repositories;

import api.lineCheck.domain.service.DriverService;
import api.lineCheck.infra.interfaces.JPAs.DriverServiceJPArepositories;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPADriverService implements IRepository<DriverService> {
    private final DriverServiceJPArepositories db;
    @Autowired
    public JPADriverService(DriverServiceJPArepositories db) {
        this.db = db;
    }
    @Override
    public void create(DriverService data) {
        db.save(data);
    }
}

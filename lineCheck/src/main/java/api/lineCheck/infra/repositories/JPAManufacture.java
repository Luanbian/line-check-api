package api.lineCheck.infra.repositories;

import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.infra.interfaces.persistances.IRepository;
import api.lineCheck.infra.interfaces.JPAs.ManufactureJPArepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPAManufacture implements IRepository<Manufacture> {
    private final ManufactureJPArepositories db;
    @Autowired
    public JPAManufacture (ManufactureJPArepositories db) {
        this.db = db;
    }
    @Override
    public void create(Manufacture data) {
        db.save(data);
    }
}

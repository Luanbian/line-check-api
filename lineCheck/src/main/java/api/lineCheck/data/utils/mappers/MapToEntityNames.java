package api.lineCheck.data.utils.mappers;

import api.lineCheck.data.utils.entities.EntityNames;

import java.util.UUID;

public class MapToEntityNames {
    public static EntityNames main(Object[] item) {
        return new EntityNames(
                (UUID) item[0],
                (String) item[1],
                (String) item[2]
        );
    }
}

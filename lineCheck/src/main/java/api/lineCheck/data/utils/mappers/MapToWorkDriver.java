package api.lineCheck.data.utils.mappers;

import api.lineCheck.data.utils.entities.WorkDriver;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public class MapToWorkDriver {
    public static WorkDriver main(Object[] item) {
        return new WorkDriver(
                (UUID) item[0],
                (String) item[1],
                (Time) item[2],
                (Time) item[3],
                (Time) item[4],
                (String) item[5],
                (String) item[6],
                (String) item[7],
                (String) item[8],
                (List<DayOfWeek>) item[9]
        );
    }
}

package api.lineCheck.data.utils.mappers;

import api.lineCheck.data.utils.entities.WorkManager;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public class MapToWorkManager {
    public static WorkManager main(Object[] item) {
        return new WorkManager(
                (UUID) item[0],
                (String) item[1],
                (Time) item[2],
                (Timestamp) item[3],
                (Time) item[4],
                (Timestamp) item[5],
                (Time) item[6],
                (Timestamp) item[7],
                (String) item[8],
                (String) item[9],
                (String) item[10],
                (String) item[11],
                (List<DayOfWeek>) item[12]
        );
    }
}

package api.lineCheck.domain.work;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public record WorkProps(
        UUID accountId,
        UUID serviceId,
        List<DayOfWeek> daysOfTheWeeks,
        Time startJourneyModel,
        Time startLineModel,
        Time endLineModel,
        UUID logisticId,
        UUID vehicleId,
        UUID manufactureId
) {}

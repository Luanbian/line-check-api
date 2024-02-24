package api.lineCheck.domain.work;

import api.lineCheck.domain.week.DaysOfTheWeek;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

public record WorkProps(
        UUID accountId,
        UUID serviceId,
        List<DaysOfTheWeek> daysOfTheWeeks,
        Time startJourneyModel,
        Time startLineModel,
        Time endLineModel,
        UUID logisticId,
        UUID vehicleId,
        UUID manufactureId
) {}

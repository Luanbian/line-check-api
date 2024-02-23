package api.lineCheck.domain.work;

import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.service.Service;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.domain.week.DaysOfTheWeek;

import java.io.Serial;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

public record WorkProps(
        Account account,
        Service service,
        List<DaysOfTheWeek> daysOfTheWeeks,
        Time startJourneyModel,
        Time startLineModel,
        Time endLineModel,
        Logistic logistic,
        Vehicle vehicle,
        Manufacture manufacture
) {}

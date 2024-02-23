package api.lineCheck.domain.work;

import api.lineCheck.domain.account.Account;
import api.lineCheck.domain.logistic.Logistic;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.service.DriverService;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.domain.week.DaysOfTheWeek;

import java.sql.Time;
import java.util.List;

public record WorkProps(
        Account account,
        DriverService service,
        List<DaysOfTheWeek> daysOfTheWeeks,
        Time startJourneyModel,
        Time startLineModel,
        Time endLineModel,
        Logistic logistic,
        Vehicle vehicle,
        Manufacture manufacture
) {}

package api.lineCheck.data.utils.mappers;

import java.time.DayOfWeek;

public class ConvertStringToDayOfWeek {
    public static DayOfWeek main(String item) {
        return switch (item) {
            case "SUNDAY" -> DayOfWeek.SUNDAY;
            case "MONDAY" -> DayOfWeek.MONDAY;
            case "TUESDAY" -> DayOfWeek.TUESDAY;
            case "WEDNESDAY" -> DayOfWeek.WEDNESDAY;
            case "THURSDAY" -> DayOfWeek.THURSDAY;
            case "FRIDAY" -> DayOfWeek.FRIDAY;
            case "SATURDAY" -> DayOfWeek.SATURDAY;
            default -> throw new IllegalStateException("Unexpected day of the week: " + item);
        };
    }
}

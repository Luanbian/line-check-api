package api.lineCheck.domain.work;
import lombok.*;
import java.sql.*;
import java.util.*;
@Data
public class Work {
    private UUID id;
    private String driver;
    private Time startJourneyModel;
    private Time startLineModel;
    private Time endExpedientModel;
    private Timestamp startJourneyReal;
    private Timestamp startLineReal;
    private Timestamp endExpedientReal;
    private String service;
    private String road;
    private String manufacture;
    private String vehicle;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;
    public static List<Work> mapper(List<Object[]> arrays) {
        List<Work> works = new ArrayList<>();
        for (Object[] array : arrays) {
            works.add(mapToWork(array));
        }
        return works;
    }
    private static Work mapToWork(Object[] array) {
        Work work = new Work();
        work.setId(UUID.randomUUID());
        work.setDriver((String) array[0]);
        work.setStartJourneyModel((Time) array[1]);
        work.setStartLineModel((Time) array[2]);
        work.setEndExpedientModel((Time) array[3]);
        work.setStartJourneyReal((Timestamp) array[4]);
        work.setStartLineReal((Timestamp) array[5]);
        work.setEndExpedientReal((Timestamp) array[6]);
        work.setService((String) array[7]);
        work.setRoad((String) array[8]);
        work.setManufacture((String) array[9]);
        work.setVehicle((String) array[10]);
        work.setMonday((Boolean) array[11]);
        work.setTuesday((Boolean) array[12]);
        work.setWednesday((Boolean) array[13]);
        work.setThursday((Boolean) array[14]);
        work.setFriday((Boolean) array[15]);
        work.setSaturday((Boolean) array[16]);
        work.setSunday((Boolean) array[17]);
        return work;
    }
}

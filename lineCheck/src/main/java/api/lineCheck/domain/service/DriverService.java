package api.lineCheck.domain.service;

import api.lineCheck.domain.work.Work;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Service")
@Table(name = "Services")
public class DriverService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String service;
    @OneToMany(mappedBy = "service")
    private List<Work> works;
    private DriverService (String data) {
        this.service = data;
    }
    public static DriverService create (String data) {
        return new DriverService(data);
    }
}

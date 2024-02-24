package api.lineCheck.domain.service;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    private List<UUID> workListIds;
}

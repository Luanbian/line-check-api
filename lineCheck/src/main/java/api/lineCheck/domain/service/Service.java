package api.lineCheck.domain.service;

import api.lineCheck.domain.work.Work;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Service")
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String service;
    @OneToMany(mappedBy = "service")
    private List<Work> workList;
}

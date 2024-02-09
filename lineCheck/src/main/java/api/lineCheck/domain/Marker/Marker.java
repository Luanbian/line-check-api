package api.lineCheck.domain.Marker;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;
@Data
@Entity(name = "Marker")
@Table(name = "Markers")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Timestamp startJourney;
    private Timestamp startLine;
    private Timestamp endExpedient;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
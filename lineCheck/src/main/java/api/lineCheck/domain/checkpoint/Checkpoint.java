package api.lineCheck.domain.checkpoint;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity(name = "Checkpoint")
@Table(name = "Checkpoints")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Checkpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Timestamp startJourney;
    private Timestamp startLine;
    private Timestamp endExpedient;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Account account;
}

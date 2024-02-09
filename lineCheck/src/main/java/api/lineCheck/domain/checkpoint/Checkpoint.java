package api.lineCheck.domain.checkpoint;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Time;
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
    private Time startJourney;
    private Time startLine;
    private Time endExpedient;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

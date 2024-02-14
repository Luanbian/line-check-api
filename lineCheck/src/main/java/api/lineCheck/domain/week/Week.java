package api.lineCheck.domain.week;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Week")
@Table(name = "Weeks")
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
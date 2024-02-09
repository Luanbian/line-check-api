package api.lineCheck.domain.manufacture;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Manufacture")
@Table(name = "Manufactures")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String manufacture;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
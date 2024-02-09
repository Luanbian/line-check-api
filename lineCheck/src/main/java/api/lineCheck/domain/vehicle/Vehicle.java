package api.lineCheck.domain.vehicle;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Vehicle")
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String vehicle;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
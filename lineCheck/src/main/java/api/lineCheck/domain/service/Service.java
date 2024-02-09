package api.lineCheck.domain.service;

import api.lineCheck.domain.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
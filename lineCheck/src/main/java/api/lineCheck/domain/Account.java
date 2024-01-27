package api.lineCheck.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Entity(name = "Account")
@Table(name = "Accounts")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String password;

    private Account (AccountProps props) {
        this.name = props.name();
        this.email = props.email();
        this.phone = props.phone();
        this.password = props.password();
    }

    public static Account create (AccountProps props) {
        return new Account(props);
    }
}
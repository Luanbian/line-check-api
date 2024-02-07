package api.lineCheck.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Data
@Entity(name = "Account")
@Table(name = "Accounts")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;

    private Account (AccountProps props) {
        this.name = props.name();
        this.email = props.email();
        this.phone = props.phone();
        this.password = props.password();
        this.role = props.role();
    }

    public static Account create (AccountProps props) {
        return new Account(props);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.MANAGER) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_DRIVER")
            );
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_DRIVER"));
        }
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
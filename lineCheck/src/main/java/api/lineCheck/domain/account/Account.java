package api.lineCheck.domain.account;

import api.lineCheck.domain.work.Work;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Data
@Entity(name = "Account")
@Table(name = "Accounts")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Work> workList;
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
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        return authorities;
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
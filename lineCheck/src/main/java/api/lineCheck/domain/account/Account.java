package api.lineCheck.domain.account;

import api.lineCheck.domain.Marker.Marker;
import api.lineCheck.domain.checkpoint.Checkpoint;
import api.lineCheck.domain.manufacture.Manufacture;
import api.lineCheck.domain.road.Road;
import api.lineCheck.domain.service.Service;
import api.lineCheck.domain.vehicle.Vehicle;
import api.lineCheck.domain.week.Week;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Checkpoint> checkpointList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Marker> marker;
    @OneToMany(mappedBy = "account")
    private List<Week> week;
    @OneToMany(mappedBy = "account")
    private List<Service> serviceList;
    @OneToMany(mappedBy = "account")
    private List<Road> roadList;
    @OneToMany(mappedBy = "account")
    private List<Vehicle> vehicleList;
    @OneToMany(mappedBy = "account")
    private List<Manufacture> manufactureList;

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
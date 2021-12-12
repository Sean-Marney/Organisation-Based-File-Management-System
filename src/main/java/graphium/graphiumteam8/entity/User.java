package graphium.graphiumteam8.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(nullable = false)
    private String firstName = "";

    @Column(nullable = false)
    private String lastName = "";

    @Column(nullable = false)
    private String username;

    @Column(nullable = false) // length 64 matches bcrypt
    private String password; // Encode in bcrypt

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getUsername() {
        return username;
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
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

}

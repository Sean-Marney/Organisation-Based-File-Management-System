package graphium.graphiumteam8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "first_name", nullable = false)
//    private String firstName;

//    @Column(name = "last_name", nullable = false)
//    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false) // length 64 matches bcrypt
    private String password; // Encode in bcrypt

    @Column(name = "role", nullable = false)
    private String role;

    //    @Column(name = "enabled", nullable = false)
    @Column(name = "enabled", columnDefinition = "TINYINT(1)")
    private Boolean enabled;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    public User() {
//    }
//        this.username = username;
//        this.password = password;
//        this.role = "USER";
//        this.enabled = Boolean.TRUE;


//    public List<GrantedAuthority> getRoles() {
//    }
}

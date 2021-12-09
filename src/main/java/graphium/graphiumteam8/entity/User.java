package graphium.graphiumteam8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false) // length 64 matches bcrypt
    private String password; // Encode in bcrypt

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<File> userFiles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;


//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();

//    public User() {
//    }
//        this.username = username;
//        this.password = password;
//        this.role = "USER";
//        this.enabled = Boolean.TRUE;


//    public List<GrantedAuthority> getRoles() {
//    }
}

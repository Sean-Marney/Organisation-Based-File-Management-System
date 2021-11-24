package graphium.graphiumteam8.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45)
    private String fname;

    @Column(nullable = false, length = 45)
    private String lname;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 64) // length 64 matches bcrypt
    private String password; // Encode in bcrypt
}

package graphium.graphiumteam8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organisations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organisation_id")
    private int id;

    @Column(name = "organisation_name")
    private String organisationName;

    // Organisation's list of partners
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organisation_id")
    private List<PartnerOrganisation> partnerOrganisations = new ArrayList<>();

    // Organisation's list of users
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organisation_id")
    private List<User> usersOfOrganisation = new ArrayList<>();
}

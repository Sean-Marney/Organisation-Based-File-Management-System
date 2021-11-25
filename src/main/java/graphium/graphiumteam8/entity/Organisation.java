package graphium.graphiumteam8.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "organisations")
@Data
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organisation_id")
    private long id;

    @Column(name = "organisation_name")
    private String organisationName;

    @Column(name = "organisation_description")
    private String organisationDescription;


}

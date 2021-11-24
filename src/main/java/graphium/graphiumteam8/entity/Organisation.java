package graphium.graphiumteam8.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "organisations")
@Data
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "organisation_name")
    private String orgName;

    @Column(name = "organisation_description")
    private String orgDescription;


}

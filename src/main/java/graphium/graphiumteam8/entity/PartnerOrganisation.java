package graphium.graphiumteam8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "partner_organisations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerOrganisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_organisation_id")
    private int id;

    @Column(name = "partner_organisation_name")
    private String partnerOrganisationName;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}

package graphium.graphiumteam8.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "partner_organisations")
@Data
public class PartnerOrganisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organisation_id")
    private Long id;

    @Column(name = "partner_organisation_id")
    private Long partnerOrganisationId;

    @Column(name = "partner_organisation_name")
    private String partnerOrganisationName;

    @ManyToOne
    private Organisation organisation;
}

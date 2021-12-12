package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.PartnerOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerOrganisationRepository extends JpaRepository<PartnerOrganisation, Integer> {

    List<PartnerOrganisation> findAll();

}

package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationDAO extends JpaRepository<Organisation, Long> {

    List<Organisation> findAll();
}

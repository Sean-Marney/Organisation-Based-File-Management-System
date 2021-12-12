package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    // Returns list of organisation objects
    public List<Organisation> listOrganisations() {

        return organisationRepository.findAll();
    }

    // Returns list of organisation names to reference the objects by
    public List<String> listOrganisationNames() {

        List<String> listOfOrganisationNames = new ArrayList<>();

        for (Organisation organisation : listOrganisations()) {
            listOfOrganisationNames.add(organisation.getOrganisationName());
        }

        return listOfOrganisationNames;
    }

    public void save(Organisation organisation) {
        organisationRepository.save(organisation);
    }
}

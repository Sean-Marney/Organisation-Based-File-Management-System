package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.repository.OrganisationDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {

    private final OrganisationDAO organisationDAO;

    public OrganisationService(OrganisationDAO organisationDAO) {
        this.organisationDAO = organisationDAO;
    }

    // Returns list of organisation objects
    public List<Organisation> listOrganisations(){

        return organisationDAO.findAll();
    }

    // Returns list of organisation names to reference the objects by
    public List<String> listOrganisationNames(){

        List<String> listOfOrganisationNames = new ArrayList<>();

        for(Organisation organisation : listOrganisations()){
            listOfOrganisationNames.add(organisation.getOrgName());
        }

        return listOfOrganisationNames;
    }
}

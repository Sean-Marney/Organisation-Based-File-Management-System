package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.Organisation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataService {

    public List<Organisation> getListOfTestOrganisations(){

        List<Organisation> testOrganisations = new ArrayList<>();

        Organisation orgCardiffUniversity = new Organisation();
        Organisation orgCardiffMetropolitan = new Organisation();
        Organisation orgSwanseaUniversity = new Organisation();

        orgCardiffUniversity.setId(1);
        orgCardiffUniversity.setOrganisationName("Cardiff University");

        orgCardiffMetropolitan.setId(2);
        orgCardiffMetropolitan.setOrganisationName("Cardiff Metropolitan");

        orgSwanseaUniversity.setId(3);
        orgSwanseaUniversity.setOrganisationName("Swansea University");

        testOrganisations.add(orgCardiffUniversity);
        testOrganisations.add(orgCardiffMetropolitan);
        testOrganisations.add(orgSwanseaUniversity);

        return testOrganisations;
    }

}

package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.PartnerOrganisation;
import graphium.graphiumteam8.repository.PartnerOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerOrganisationService {

    private final PartnerOrganisationRepository partnerOrganisationRepository;

    @Autowired
    public PartnerOrganisationService(PartnerOrganisationRepository partnerOrganisationRepository) {
        this.partnerOrganisationRepository = partnerOrganisationRepository;
    }

    public List<PartnerOrganisation> listPartnerOrganisations() {

        return partnerOrganisationRepository.findAll();
    }

    public List<String> listPartnerOrganisationNames() {

        List<String> listOfPartnerOrganisationNames = new ArrayList<>();

        for (PartnerOrganisation partnerOrganisation : listPartnerOrganisations()) {
            listOfPartnerOrganisationNames.add(partnerOrganisation.getPartnerOrganisationName());
        }

        return listOfPartnerOrganisationNames;
    }
}

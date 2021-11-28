package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.repository.OrganisationDAO;
import graphium.graphiumteam8.service.OrganisationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrganisationController {

    private final OrganisationService organisationService;
    private final OrganisationDAO organisationDAO;

    public OrganisationController(OrganisationService organisationService, OrganisationDAO organisationDAO) {
        this.organisationService = organisationService;
        this.organisationDAO = organisationDAO;
    }

    @GetMapping("/organisations")
    public String getOrganisations(Model model){

        List<String> listOfOrganisationNames = organisationService.listOrganisationNames();
        model.addAttribute("listOfOrganisationNames", listOfOrganisationNames);

        return "organisations";
    }

    @GetMapping("/organisations/create")
    public String getCreateOrganisationForm(Model model){

        // Create new organisation object to be sent to form
        model.addAttribute("organisation", new Organisation());

        return "create-organisation-form";
    }

    @PostMapping("/organisations/create-form")
    public String createOrganisationForm(Organisation organisation){

        organisationDAO.save(organisation);

        return "redirect:/organisations";
    }
}

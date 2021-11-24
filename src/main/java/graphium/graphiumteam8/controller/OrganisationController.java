package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrganisationController {

    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping("/organisations")
    public String getOrganisations(Model model){

        List<String> listOfOrganisationNames = organisationService.listOrganisationNames();
        model.addAttribute("listOfOrganisationNames", listOfOrganisationNames);

        return "organisations";
    }
}

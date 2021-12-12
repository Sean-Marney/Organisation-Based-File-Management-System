package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Organisation;
import graphium.graphiumteam8.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationService organisationService;

    @GetMapping("/organisations")
    public String getOrganisations(Model model) {

        List<String> listOfOrganisationNames = organisationService.listOrganisationNames();
        model.addAttribute("listOfOrganisationNames", listOfOrganisationNames);

        return "organisations";
    }

    @GetMapping("/organisations/create")
    public String getCreateOrganisationForm(Model model) {
        model.addAttribute("organisation", new Organisation());
        return "create-organisation-form";
    }

    @PostMapping("/organisations/create-form")
    public String createOrganisationForm(Organisation organisation) {
        organisationService.save(organisation);
        return "redirect:/organisations";
    }
}

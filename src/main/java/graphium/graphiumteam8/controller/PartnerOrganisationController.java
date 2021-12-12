package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.service.PartnerOrganisationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PartnerOrganisationController {

    private final PartnerOrganisationService partnerOrganisationService;

    public PartnerOrganisationController(PartnerOrganisationService partnerOrganisationService) {
        this.partnerOrganisationService = partnerOrganisationService;
    }

    @GetMapping("/partner-organisations")
    public String getPartnerOrganisations(Model model) {

        List<String> listOfPartnerOrganisationNames = partnerOrganisationService.listPartnerOrganisationNames();
        model.addAttribute("listOfPartnerOrganisationNames", listOfPartnerOrganisationNames);

        return "partner-organisations";
    }

}

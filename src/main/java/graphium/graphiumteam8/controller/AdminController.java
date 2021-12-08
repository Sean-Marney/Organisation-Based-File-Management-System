package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/organisation")
    public String getAdmin() {
        return "organisation";
    }
}

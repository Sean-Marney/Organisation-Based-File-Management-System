package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

// Home controller is responsible for viewing the index page

@Controller
public class HomeController {

    @GetMapping({"/", "index"})
    public String index(Model model){
        model.addAttribute("today", LocalDate.now());
        return "index";
    }
    
}

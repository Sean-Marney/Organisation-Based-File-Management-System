package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Home controller is responsible for viewing the index page

@Controller
public class HomeController {

    @GetMapping({"/", "index"})
    public String index(){
        return "index";
    }
    
}

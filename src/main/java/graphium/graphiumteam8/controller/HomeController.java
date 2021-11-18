package graphium.graphiumteam8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// Home controller is responsible for viewing the index page

@Controller
public class HomeController {

    @GetMapping({"/", "index"})
    public String index(){
        return "index";
    }

    @GetMapping("/file-system")
    public ModelAndView uploadFile(){
        return new ModelAndView("file-system");
    }

    @GetMapping("/view-all-files")
    public ModelAndView viewAllFiles(){
        return new ModelAndView("view-all-files");
    }
}

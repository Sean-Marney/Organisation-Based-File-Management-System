package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Controller for viewing different pages of the file system application

@Controller
public class FileHomeController {

    private final FileService fileService;

    public FileHomeController(FileService fileService) {
        this.fileService = fileService;
    }

    // Returns list of file names from database as its is needed in URL to download correlating file
    @GetMapping("/files") // TODO "/files/{username}"
    public String getFiles(Model model) {

        List<String> listOfFileNames = fileService.listFileNames();
        model.addAttribute("listOfFileNames", listOfFileNames);

        return "files";
    }

    // Returns the file upload form
    @GetMapping("/files/upload")
    public String getUploadForm(){

        return "file-upload";
    }
}

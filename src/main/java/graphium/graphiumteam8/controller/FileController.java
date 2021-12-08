package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.controller.forms.SetFileAccessForm;
import graphium.graphiumteam8.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

// Controller for functionality of the file system

@Controller
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Returns list of file names from database as its is needed in URL to download correlating file
    @GetMapping("/files")
    public String getFiles(Model model) {

        // User's files
        List<String> listOfFileNames = fileService.listFileNames();
        model.addAttribute("listOfFileNames", listOfFileNames);

        // User's public files ( TODO: find a way to let others see the files depending on access - secure methods so that only those roles can access it)
        List<String> publicFiles = fileService.publicFiles;
        model.addAttribute("listOfPublicFiles", publicFiles);

        List<String> organisationFiles = fileService.organisationFiles;
        model.addAttribute("listOfOrganisationFiles", organisationFiles);

        List<String> partnerOrganisationFiles = fileService.partnerOrganisationFiles;
        model.addAttribute("listOfPartnerOrganisationFiles", partnerOrganisationFiles);

        return "files";
    }

    // Returns the file upload form
    @GetMapping("/files/upload")
    public String getUploadForm(){

        return "file-upload";
    }

    // This endpoint allows user to select their own file and upload it to the database
    @PostMapping("/upload")
    String fileUpload(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {

        return fileService.fileUpload(multipartFile, redirectAttributes);
    }

    // Returns file access form
    @GetMapping("/files/file-access/{fileName}")
    public String fileAccessForm(@PathVariable String fileName, Model model){

        model.addAttribute("setFileAccessForm", new SetFileAccessForm());

        return "file-access";
    }

    @PostMapping("/files/file-access/{fileName}")
    public String fileAccessFormSubmit(@PathVariable String fileName, @ModelAttribute SetFileAccessForm setFileAccessForm, Model model){
        model.addAttribute("setFileAccessForm", setFileAccessForm);

            // Adds files to public file table
        if (Objects.equals(setFileAccessForm.getFileAccessPublic(), "choicePublic")){
            fileService.setFileAccessToPublic(fileName);
            return "redirect:/files";

            // Adds files to organisation file table
        } else if (Objects.equals(setFileAccessForm.getFileAccessMyOrganisation(), "choiceMyOrganisation")){
            fileService.setFileAccessToMyOrganisation(fileName);
            return "redirect:/files";

            // Adds files to partner organisation file table
        } else if (Objects.equals(setFileAccessForm.getFileAccessPartnerOrganisation(), "choicePartnerOrganisation")){
            fileService.setFileAccessToPartnerOrganisation(fileName);
            return "redirect:/files";

        } else {

            return "redirect:/";
        }
    }

    @GetMapping("/files/view/{fileName}")
    ResponseEntity<byte[]> viewFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        return fileService.viewFile(fileName, httpServletRequest);
    }

    @GetMapping("/files/download/{fileName}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        return fileService.downloadFile(fileName, httpServletRequest);
    }
}

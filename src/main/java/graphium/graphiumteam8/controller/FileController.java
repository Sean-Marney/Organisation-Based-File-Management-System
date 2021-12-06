package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.controller.forms.SetFileAccessForm;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileRepository;
import graphium.graphiumteam8.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

// Controller for functionality of the file system

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Returns list of file names from database as its is needed in URL to download correlating file
    @GetMapping("/files")
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

        // Choices from radio button results
        if (Objects.equals(setFileAccessForm.getFileAccessEveryone(), "choiceEveryone")){
            fileService.setFileAccessToEveryone();
            return "redirect:/files";

        } else if (Objects.equals(setFileAccessForm.getFileAccessMyOrganisation(), "choiceMyOrganisation")){
            fileService.setFileAccessToMyOrganisation();
            return "redirect:/files";

        } else if (Objects.equals(setFileAccessForm.getFileAccessOtherOrganisation(), "choiceOtherOrganisation")){
            fileService.setFileAccessToOtherOrganisation();
            return "redirect:/files";

        } else if (Objects.equals(setFileAccessForm.getFileAccessMyself(), "choiceMyself")){
            fileService.setFileAccessToMyself();
            return "redirect:/files";

        } else {

            return "redirect:/";
        }
    }

    // This endpoint allows the user to download/view a file from the database by its file name
    @GetMapping("/files/view/{fileName}")
    ResponseEntity<byte[]> viewFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        return fileService.viewFile(fileName, httpServletRequest);
    }

    @GetMapping("/files/download/{fileName}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        return fileService.downloadFile(fileName, httpServletRequest);
    }
}

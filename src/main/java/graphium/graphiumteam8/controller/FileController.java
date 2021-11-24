package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.controller.forms.SetFileAccessForm;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import graphium.graphiumteam8.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
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

    Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;
    private final FileDAO fileDAO;

    public FileController(FileDAO fileDAO, FileService fileService) {
        this.fileDAO = fileDAO;
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
    String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException { // MultipartFile allows for file upload as "file"

        // Getting file name
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        System.out.println("Log: fileName is " + name);

        // Getting file type
        String fileType = file.getContentType();

        // Getting file URL
        String url = ServletUriComponentsBuilder.fromCurrentContextPath() // CurrentContextPath returns current URL (so, localhost:8080)
                .path("/download/") // localhost:8080/download
                .path(name)         // localhost:8080/download/my_file.pdf
                .toUriString(); // Builds string to create URL, which contains the file content

        // Creating new file object, containing user's file, and saving it to the database via fileDAO
        File newFile = new File();
        newFile.setFileName(name);
        newFile.setFileObject(file.getBytes());
        fileDAO.save(newFile); // Saves file to database

        // Alert box after successful upload
        redirectAttributes.addFlashAttribute("uploadMessage", "File upload successful");

        //return "redirect:/files/view/" + name; // Displays file from the browser
        return "redirect:/files/upload";
    }

    // Returns file access form
    @GetMapping("/files/file-access/{fileName}")
    public String fileAccessForm(@PathVariable String fileName, Model model){

        model.addAttribute("setFileAccessForm", new SetFileAccessForm());

        return "file-access";
    }

    // TODO Only the first if statement sets the variable - Maybe use list instead of radio btn
    @PostMapping("/files/file-access/{fileName}")
    public String fileAccessFormSubmit(@PathVariable String fileName, @ModelAttribute SetFileAccessForm setFileAccessForm, Model model){
        model.addAttribute("setFileAccessForm", setFileAccessForm);

        if (setFileAccessForm.getFileAccessEveryone().equals("choiceEveryone")){ // Choices from radio button results
            fileService.setFileAccessToEveryone(); // Set file access
            logger.info("Called setFileAccess");
            return "redirect:/files";

        } else if (setFileAccessForm.getFileAccessMyOrganisation().equals("choiceMyOrganisation")){
            fileService.setFileAccessToMyOrganisation();
            logger.info("Called setFileAccessToMyOrganisation");
            return "redirect:/files";

        } else if (setFileAccessForm.getFileAccessOtherOrganisation().equals("choiceOtherOrganisation")){
            fileService.setFileAccessToOtherOrganisation();
            logger.info("Called setFileAccessToOtherOrganisation");
            return "redirect:/files";

        } else if (setFileAccessForm.getFileAccessSpecificUser().equals("choiceSpecificUser")){
            fileService.setFileAccessToSpecificUser();
            logger.info("Called setFileAccessToSpecificUser");
            return "redirect:/files";

        } else if (setFileAccessForm.getFileAccessMyself().equals("choiceMyself")){
            fileService.setFileAccessToMyself();
            logger.info("Called setFileAccessToMyself");
            return "redirect:/files";
        }

        return "redirect:/files";
    }

    // This endpoint allows the user to download/view a file from the database by its file name
    @GetMapping("/files/view/{fileName}")
    ResponseEntity<byte[]> viewFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        File file = fileDAO.findByFileName(fileName);

        // Stores file type
        String mediaType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        // Returns browser view of file that was just uploaded to database
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()) // Renders file to browser (inline)
                .body(file.getFileObject());
    }

    @GetMapping("/files/download/{fileName}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        File file = fileDAO.findByFileName(fileName);

        // Stores file type
        String mediaType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        // Returns download attachment instead of browser view of the file
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + file.getFileName())
                .body(file.getFileObject());
    }

    // File share

    // TODO Currently not working properly
    @GetMapping("/files/search")
    public String fileSearch(@Param("searchTerm") String searchTerm, Model model){

        List<File> searchResult = fileService.fileSearch(searchTerm);

        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("pageTitle", "Search results for '" +searchTerm+ "'");
        model.addAttribute("searchResult", searchResult);

        return "file-search-results";
    }
}

package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.dto.FileUploadDetailsDTO;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import graphium.graphiumteam8.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class FileController {

    String name;

    private final FileService fileService;
    private final FileDAO fileDAO;

    public FileController(FileDAO fileDAO, FileService fileService) {
        this.fileDAO = fileDAO;
        this.fileService = fileService;
    }


    // This endpoint allows user to select their own file and upload it to the database
    @PostMapping("/upload")
    String fileUpload(@RequestParam("file")MultipartFile file) throws IOException { // MultipartFile allows for file upload as "file"

        // Getting file name
        name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Getting file type
        String fileType = file.getContentType();

        // Getting file URL
        // CurrentContextPath returns current URL (so, localhost:8080)
        // localhost:8080/download
        // localhost:8080/download/my_file.pdf
        String url = ServletUriComponentsBuilder.fromCurrentContextPath() // CurrentContextPath returns current URL (so, localhost:8080)
                .path("/download/") // localhost:8080/download
                .path(name)         // localhost:8080/download/my_file.pdf
                .toUriString(); // Builds string to create URL, which contains the file content

        // Creating new file object, containing user's file, and saving it to the database via fileDAO
        File newFile = new File();
        newFile.setFileName(name);
        newFile.setFileObject(file.getBytes());
        fileDAO.save(newFile); // Saves file to database

        return "redirect:/download/"+name; // Displays file from the browser
    }

    // TODO: NonUniqueResultException when file already exists
    // This endpoint allows the user to download/view a file from the database by its file name
    @GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        File file = fileDAO.findByFileName(fileName);

        String mediaType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()) // Renders file to browser (inline)
                .body(file.getFileObject());
    }

    // TODO Returns /download/null instead of returning the file
    @GetMapping("/fileSearch")
    public String fileSearch(@Param("searchTerm") String searchTerm, Model model){

        List<File> searchResult = fileService.fileSearch(searchTerm);

        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("pageTitle", "Search results for '" +searchTerm+ "'");
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("fileName", name);

        return "file-search-results";
    }
}

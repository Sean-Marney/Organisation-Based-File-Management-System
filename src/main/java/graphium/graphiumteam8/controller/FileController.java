package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import graphium.graphiumteam8.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

// Controller should not have business logic (change this)

@Controller
public class FileController {


    private final FileService fileService;
    private final FileDAO fileDAO;

    public FileController(FileDAO fileDAO, FileService fileService) {
        this.fileDAO = fileDAO;
        this.fileService = fileService;
    }


    // This endpoint allows user to select their own file and upload it to the database
    @PostMapping("/upload")
    String fileUpload(@RequestParam("file") MultipartFile file) throws IOException { // MultipartFile allows for file upload as "file"

        // Getting file name
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        System.out.println("TEST: + " +name);

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

        return "redirect:/download/" + name; // Displays file from the browser
    }




    // TODO Stop using this endpoint for downloading list of files, it is just to get the file that has been uploaded - create another endpoint for getting files from database
    // TODO YOU CAN GET FROM DB BY ID findById ?? maybe
    // This endpoint allows the user to download/view a file from the database by its file name
    @GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {

        // File name is null from the start of this method
        // Need to get names from database, not previous variables... Why would that work?
        File file = fileDAO.findByFileName(fileName);

        String mediaType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        // Returns browser view of file that was just uploaded to database
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()) // Renders file to browser (inline)
                .body(file.getFileObject());
    }

    // Returns list of file names from database as its is needed in URL to download correlating file
    @GetMapping("/files")
    public String getFiles(Model model){

        List<String> listOfFileNames = fileService.listFileNames();
        model.addAttribute("listOfFileNames", listOfFileNames);

        return "files";
    }

    /*

    @GetMapping("/viewFiles")
    public String viewFiles(Model model){

        List<File[]> fileList = fileDAO.findFiles();
        model.addAttribute("fileList", fileList);

        return "view-all-files";
    }

    // TODO (major) Returns /download/null instead of returning the file due to file name=null
    @GetMapping("/fileSearch")
    public String fileSearch(@Param("searchTerm") String searchTerm, Model model){

        List<File> searchResult = fileService.fileSearch(searchTerm);

        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("pageTitle", "Search results for '" +searchTerm+ "'");
        model.addAttribute("searchResult", searchResult);

        return "file-search-results";
    }

    @GetMapping("/file-download")
    public void fileDownload(HttpServletResponse httpServletResponse) throws IOException {

        // Google iterating through records in DB
        // For each file in table
        // Set fileId to correlating database id
        Long fileId = 2008L;
        File file = fileDAO.findById(fileId).get();

        httpServletResponse.setContentType("application/octet-stream"); // Download file, not open
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getFileName();
        httpServletResponse.setHeader(headerKey, headerValue);

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        // Writing content of file to the output stream
        servletOutputStream.write(file.getFileObject());
    }

     */
}

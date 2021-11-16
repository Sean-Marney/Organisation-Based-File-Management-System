package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.dto.FileUploadDetailsDTO;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@RestController
public class FileController {

    @Autowired FileDAO fileDAO;

    // Endpoint allows user to select their own file and upload it to the database
    @PostMapping("/upload")
    FileUploadDetailsDTO fileUpload(@RequestParam("file")MultipartFile file) throws IOException { // MultipartFile allows for file upload as "file"

        // Getting file name
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

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
        fileDAO.save(newFile);

        return new FileUploadDetailsDTO(name, fileType, url); // Sends user's file to database
    }
}

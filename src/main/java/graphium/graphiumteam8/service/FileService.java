package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Business logic for the file system which is passed to controller

@Service
public class FileService{
    Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    // Returns list of files
    public List<File> listFiles(){

        return fileRepository.findAll();
    }

    // Returns list of file names
    public List<String> listFileNames(){

        List<String> listOfFileNames = new ArrayList<>();

        for(File file : listFiles()){
            listOfFileNames.add(file.getFileName());
        }

        return listOfFileNames;
    }

    // Allows user to upload their own file
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException { // MultipartFile allows for file upload as "file"

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        String fileType = multipartFile.getContentType(); // Currently not being used

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath() // CurrentContextPath returns current URL (so, localhost:8080)
                .path("/download/") // localhost:8080/download
                .path(fileName) // localhost:8080/download/my_file.pdf
                .toUriString(); // Builds string to create URL, which contains the file content

        // Creating new file object, containing user's file, and saving it to the database
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileObject(multipartFile.getBytes());
        fileRepository.save(newFile); // Saves file to database

        redirectAttributes.addFlashAttribute("uploadMessage", "File upload successful"); // Alerts user of successful upload

        return "redirect:/files/upload";
    }

    public ResponseEntity<byte[]> viewFile(@PathVariable String fileName, HttpServletRequest httpServletRequest){

        File file = fileRepository.findByFileName(fileName);

        String fileType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()) // Renders file inline to browser
                .body(file.getFileObject());
    }

    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest){

        File file = fileRepository.findByFileName(fileName);

        String fileType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        // Returns as download attachment instead of inline browser view
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .body(file.getFileObject());
    }

    // Logic for setting the file access \\

    public void setFileAccessToEveryone(){
        logger.info("Called setFileAccessEveryone");
    }

    public void setFileAccessToMyOrganisation(){
        logger.info("Called setFileAccessToMyOrganisation");
    }

    public void setFileAccessToOtherOrganisation(){
        logger.info("Called setFileAccessToOtherOrganisation");
    }

    public void setFileAccessToMyself(){
        logger.info("Called setFileAccessToMyself");
    }
}
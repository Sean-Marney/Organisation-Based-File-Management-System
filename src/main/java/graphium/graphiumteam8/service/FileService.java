package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.FileRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Business logic for the file system which is passed to controller

@Service
@Data
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    // Returns list of files
    public List<File> listFiles() {
        return fileRepository.findAll();
    }

    // Returns list of file names
    public List<String> listFileNames() {
        List<String> listOfFileNames = new ArrayList<>();

        for (File file : listFiles()) {
            listOfFileNames.add(file.getFileName());
        }

        return listOfFileNames;
    }

    // Allows user to upload their own file
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException { // MultipartFile allows for file upload as "file"

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        // Creating new file object, containing user's file, and saving it to the database
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileObject(multipartFile.getBytes());
        newFile.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        fileRepository.save(newFile); // Saves file to database

        redirectAttributes.addFlashAttribute("uploadMessage", "File upload successful"); // Alerts user of successful upload

        return "redirect:/files/upload";
    }

    public ResponseEntity<byte[]> viewFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {
        File file = fileRepository.findByFileName(fileName).orElse(null);

        String fileType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

//        addView(file);
        fileRepository.save(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()) // Renders file inline to browser
                .body(file.getFileObject());
    }

//    private void addView(File file) {
//        final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        final FileView e = new FileView();
//        e.setUser(principal);
//        file.getViews().add(e);
//    }

    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {
        File file = fileRepository.findByFileName(fileName).orElse(null);

        String fileType = httpServletRequest.getServletContext().getMimeType(file.getFileName());

        // Returns as download attachment instead of inline browser view
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .body(file.getFileObject());
    }

    public void setFileAccessToPublic(String fileName) {
        fileRepository.findByFileName(fileName).ifPresent(file -> {
            file.setAccessType(File.FileAccessType.PUBLIC);
            fileRepository.save(file);
        });
    }

    public void setFileAccessToMyOrganisation(String fileName) {
        fileRepository.findByFileName(fileName).ifPresent(file -> {
            file.setAccessType(File.FileAccessType.ORGANISATION);
            fileRepository.save(file);
        });
    }

    public void setFileAccessToPartnerOrganisation(String fileName) {
        fileRepository.findByFileName(fileName).ifPresent(file -> {
            file.setAccessType(File.FileAccessType.PARTNER);
            fileRepository.save(file);
        });
    }

    public List<File> getPublicFiles() {
        return fileRepository.findAllByAccessType(File.FileAccessType.PUBLIC);
    }

    public List<File> getPartnerOrganisationFiles() {
        return fileRepository.findAllByAccessType(File.FileAccessType.PARTNER);
    }

    public List<File> getOrganisationFiles() {
        return fileRepository.findAllByAccessType(File.FileAccessType.ORGANISATION);
    }
}

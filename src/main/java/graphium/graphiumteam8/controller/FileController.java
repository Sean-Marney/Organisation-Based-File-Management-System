package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.controller.forms.FileViewModel;
import graphium.graphiumteam8.controller.forms.SetFileAccessForm;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Controller for functionality of the file system

@Controller
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // Returns list of file names from database as its is needed in URL to download correlating file
    @GetMapping("/files")
    public String getFiles(Model model) {

        // User's files
        List<String> listOfFileNames = fileService.listFileNames();
        model.addAttribute("listOfFileNames", listOfFileNames);

        // User's public files ( TODO: find a way to let others see the files depending on access - secure methods so that only those roles can access it)
        model.addAttribute("listOfPublicFiles", fileService.getPublicFiles().stream().map(File::getFileName).collect(Collectors.toList()));

        model.addAttribute("listOfOrganisationFiles", fileService.getOrganisationFiles().stream().map(File::getFileName).collect(Collectors.toList()));

        model.addAttribute("listOfPartnerOrganisationFiles", fileService.getPartnerOrganisationFiles().stream().map(File::getFileName).collect(Collectors.toList()));

        return "files";
    }

    // Returns the file upload form
    @GetMapping("/files/upload")
    public String getUploadForm() {
        return "file-upload";
    }

    // This endpoint allows user to select their own file and upload it to the database
    @PostMapping("/upload")
    String fileUpload(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        return fileService.fileUpload(multipartFile, redirectAttributes);
    }

    // Returns file access form
    @GetMapping("/files/file-access/{fileName}")
    public String fileAccessForm(@PathVariable String fileName, Model model) {

        model.addAttribute("setFileAccessForm", new SetFileAccessForm());

        return "file-access";
    }

    @PostMapping("/files/file-access/{fileName}")
    public String fileAccessFormSubmit(@PathVariable String fileName, @ModelAttribute SetFileAccessForm setFileAccessForm, Model model) {
        model.addAttribute("setFileAccessForm", setFileAccessForm);

        // Adds files to public file table
        if (Objects.equals(setFileAccessForm.getFileAccessPublic(), "choicePublic")) {
            fileService.setFileAccessToPublic(fileName);
            return "redirect:/files";

            // Adds files to organisation file table
        } else if (Objects.equals(setFileAccessForm.getFileAccessMyOrganisation(), "choiceMyOrganisation")) {
            fileService.setFileAccessToMyOrganisation(fileName);
            return "redirect:/files";

            // Adds files to partner organisation file table
        } else if (Objects.equals(setFileAccessForm.getFileAccessPartnerOrganisation(), "choicePartnerOrganisation")) {
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

//    @GetMapping("/files/views/{filename}")
//    public String fileViews(@PathVariable String filename, Model model) {
//        final File file = fileService.getFileRepository().findByFileName(filename).orElse(null);
//        assert file != null;
//        model.addAttribute("views", new ArrayList<>(file.getViews()
//                .stream()
//                .map(it -> new FileViewModel(
//                                it.getUser().getUsername(),
//                                it.getLast().toString(),
//                                file.getViews()
//                                        .stream()
//                                        .filter(t -> t.getUser().equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))
//                                        .count() + ""
//                        )
//                )
//                .collect(Collectors.toMap(FileViewModel::getUsername, p -> p, (p, d) -> p)) // Distinct get
//                .values()));
//        return "file-viewers";
//    }
}

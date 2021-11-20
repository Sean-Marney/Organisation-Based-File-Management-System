package graphium.graphiumteam8.service;

import graphium.graphiumteam8.dto.FileDTO;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Business logic for the file system which is passed to controller

@Service
public class FileService{

    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public List<File> fileSearch(String searchTerm){

        return fileDAO.fileSearch(searchTerm);
    }

    public List<File> listFiles(){

        return fileDAO.findAll();
    }

    public List<String> listFileNames(){

        FileDTO fileDTO = new FileDTO();

        List<String> listOfFileNames = new ArrayList<>();

        for(File file : listFiles()){
            listOfFileNames.add(file.getFileName());
        }
        return listOfFileNames;
    }
}

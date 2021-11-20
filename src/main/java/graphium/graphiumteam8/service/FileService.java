package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }



    public List<File> fileSearch(String searchTerm){

        return fileDAO.fileSearch(searchTerm);
    }
}

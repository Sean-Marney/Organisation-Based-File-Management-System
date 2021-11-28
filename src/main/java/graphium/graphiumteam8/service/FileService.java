package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Business logic for the file system which is passed to controller

@Service
public class FileService{

    Logger logger = LoggerFactory.getLogger(FileService.class);

    private File file;

    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    // Returns list of files
    public List<File> listFiles(){

        return fileDAO.findAll();
    }

    // Returns list of file names
    public List<String> listFileNames(){

        List<String> listOfFileNames = new ArrayList<>();

        for(File file : listFiles()){
            listOfFileNames.add(file.getFileName());
        }

        return listOfFileNames;
    }

    public List<File> fileSearch(String searchTerm){

        return fileDAO.fileSearch(searchTerm);
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

    public void setFileAccessToSpecificUser(){
        logger.info("Called setFileAccessToSpecificUser");
    }

}
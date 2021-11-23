package graphium.graphiumteam8.service;

import graphium.graphiumteam8.controller.forms.SetFileAccessForm;
import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.repository.FileDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Business logic for the file system which is passed to controller

@Service
public class FileService{

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

    public String setFileAccessToEveryone(){
        String log = "setFileAccessToEveryone";
        return log;
    }

    public String setFileAccessToMyOrganisation(){
        String log = "setFileAccessToMyOrganisation";
        return log;
    }

    public String setFileAccessToOtherOrganisation(){
        String log = "setFileAccessToOtherOrganisation";
        return log;
    }

    public String setFileAccessToSpecificUser(){
        String log = "setFileAccessToSpecificUser";
        return log;
    }

    public String setFileAccessToMyself(){
        String log = "setFileAccessToMyself";
        return log;
    }

}
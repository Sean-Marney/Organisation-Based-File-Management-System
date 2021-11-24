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
        return "setFileAccessToEveryone";
    }

    public String setFileAccessToMyOrganisation(){
        return "setFileAccessToMyOrganisation";
    }

    public String setFileAccessToOtherOrganisation(){
        return "setFileAccessToOtherOrganisation";
    }

    public String setFileAccessToSpecificUser(){
        return "setFileAccessToSpecificUser";
    }

    public String setFileAccessToMyself(){
        return "setFileAccessToMyself";
    }

}
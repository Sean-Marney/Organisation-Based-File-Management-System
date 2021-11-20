package graphium.graphiumteam8.dto;

// Contains details about file to be uploaded

import graphium.graphiumteam8.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    private String fileName;

    private String contentType;

    private String url;

    // Wrapper object to hold list of files submitted for view (/files)
    private List<File> dtoFileNames;

    public void addFile(File file){
        this.dtoFileNames.add(file);
    }
}

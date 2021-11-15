package graphium.graphiumteam8.dto;

// Contains details about file to be uploaded

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDetailsDTO {

    private String fileName;

    private String contentType;

    private String url;
}

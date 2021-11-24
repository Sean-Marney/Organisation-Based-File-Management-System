package graphium.graphiumteam8.controller.forms;

import lombok.Data;

import javax.persistence.Column;

// Form class for setting the access level of an uploaded file

@Data
public class SetFileAccessForm {

    private String fileAccessEveryone;

    private String fileAccessMyOrganisation;

    private String fileAccessOtherOrganisation;

    private String fileAccessSpecificUser;

    private String fileAccessMyself;
}

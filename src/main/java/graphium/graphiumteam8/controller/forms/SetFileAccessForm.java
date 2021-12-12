package graphium.graphiumteam8.controller.forms;

import lombok.Data;

// Form class for setting the access level of an uploaded file

@Data
public class SetFileAccessForm {

    private String fileAccessPublic;

    private String fileAccessMyOrganisation;

    private String fileAccessPartnerOrganisation;

    private String fileAccessSpecificUser;

    private String fileAccessMyself;
}

package graphium.graphiumteam8.controller.forms;

import lombok.Data;

// Form class for sending a file to another user

@Data
public class SetFileAccessForm {

    // Temp
    private String username = "fakeUsername123";

    private String fileAccessEveryone;

    private String fileAccessMyOrganisation;

    private String fileAccessOtherOrganisation;

    private String fileAccessSpecificUser;

    private String fileAccessMyself;
}

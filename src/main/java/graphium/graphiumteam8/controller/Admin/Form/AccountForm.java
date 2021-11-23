package graphium.graphiumteam8.controller.Admin.Form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The first name of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The first name of the user must be between 2 and 60 characters.")
    private String firstName;

    @NotEmpty(message = "The last name of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The last name of the user must be between 2 and 60 characters.")
    private String lastName;

    @NotEmpty(message = "The username of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The username of the user must be between 2 and 60 characters.")
    private String username;

    @NotEmpty(message = "The email of the user must be provided")
    @Email
    private String email;

    @NotEmpty(message = "")
    @Size(message = "")
    private String Password;

    @NotEmpty(message = "")
    @Size(message = "")
    private String confirmPassword;

    @NotEmpty(message = "The organisation name of the user must be between 2 and 60 characters.")
    private String organisation;
}

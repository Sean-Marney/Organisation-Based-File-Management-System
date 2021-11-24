package graphium.graphiumteam8.controller.Admin.Form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The first name of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The first name of the user must be between 2 and 60 characters.")
    private String firstName;

    @NotEmpty(message = "The last name of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The last name of the user must be between 2 and 60 characters.")
    private String lastName;

    @NotEmpty(message = "The username of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The username of the user must be between 2 and 60 characters.")
    private String username;

    @NotEmpty(message = "Email invalid")
    @Email
    private String email;

    //Minimum eight characters, at least one letter and one number
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @Size(min = 2, max = 50, message = "Needs to be between 2-50 characters ")
    private String password;

//    @NotEmpty(message = "")
//    @Size(message = "")
//    private String confirmPassword;

//    @ManyToOne
    @JoinColumn(name = "organisation")
    @NotEmpty(message = "The organisation name of the user must be between 2 and 60 characters.")
    private String organisation;


}

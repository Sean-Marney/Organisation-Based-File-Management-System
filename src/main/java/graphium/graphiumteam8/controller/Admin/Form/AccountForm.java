package graphium.graphiumteam8.controller.Admin.Form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountForm implements Serializable {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

//    @NotNull
//    @NotEmpty(message = "The first name of the user must be between 2 and 60 characters.")
//    @Size(min = 2, max = 60, message = "The first name of the user must be between 2 and 60 characters.")
//    private String firstName;

//    @NotNull
//    @NotEmpty(message = "The last name of the user must be between 2 and 60 characters.")
//    @Size(min = 2, max = 60, message = "The last name of the user must be between 2 and 60 characters.")
//    private String lastName;

    @NotNull
    @NotEmpty(message = "The username of the user must be between 2 and 60 characters.")
    @Size(min = 2, max = 60, message = "The username of the user must be between 2 and 60 characters.")
    private String username;

    //Minimum eight characters, at least one letter and one number
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @Size(min = 2, max = 50, message = "Needs to be between 2-50 characters ")
    private String password;

    private Boolean enabled;
//    @NotEmpty(message = "")
//    @Size(message = "")
//    private String confirmPassword;

    private String role;

//    @ManyToOne
//    @JoinColumn(name = "organisation")
//    @NotEmpty(message = "The organisation name of the user must be between 2 and 60 characters.")
//    private String organisation;


}

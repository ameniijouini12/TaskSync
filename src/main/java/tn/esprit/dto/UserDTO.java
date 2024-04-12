package tn.esprit.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.entities.ERole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@Data
public class UserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private ERole role;


}
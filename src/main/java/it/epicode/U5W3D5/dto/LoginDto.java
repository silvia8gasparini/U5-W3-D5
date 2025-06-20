package it.epicode.U5W3D5.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    private String email;
    @NotEmpty(message = "La password non può essere vuota")
    private String password;

}

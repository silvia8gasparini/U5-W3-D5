package it.epicode.U5W3D5.dto;

import it.epicode.U5W3D5.enumeration.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UtenteDto {
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String nome;
    @NotEmpty(message = "Il cognome non può essere vuoto")
    private String cognome;
    @NotEmpty(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    private String email;
    @NotEmpty(message = "La password non può essere vuota")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

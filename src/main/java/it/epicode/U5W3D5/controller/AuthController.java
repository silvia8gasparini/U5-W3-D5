package it.epicode.U5W3D5.controller;

import it.epicode.U5W3D5.dto.LoginDto;
import it.epicode.U5W3D5.dto.UtenteDto;
import it.epicode.U5W3D5.exception.NonTrovatoException;
import it.epicode.U5W3D5.exception.ValidationException;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.service.AuthService;
import it.epicode.U5W3D5.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService userService;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteDto utenteDto, BindingResult bindingResult) throws NonTrovatoException, ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (e, c) -> e + c));
        }

        return userService.saveUser(utenteDto);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginDto loginDto) throws NonTrovatoException {
        return authService.login(loginDto);
    }
}
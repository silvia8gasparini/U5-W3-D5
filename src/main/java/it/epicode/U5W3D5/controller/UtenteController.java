package it.epicode.U5W3D5.controller;

import it.epicode.U5W3D5.dto.UtenteDto;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Utente getProfilo(@AuthenticationPrincipal Utente utente) {
        return utente;
    }

    @PutMapping
    public Utente aggiornaProfilo(@AuthenticationPrincipal Utente utente,
                                  @RequestBody UtenteDto dto) {
        return utenteService.updateUtente(utente.getId(), dto);
    }
}

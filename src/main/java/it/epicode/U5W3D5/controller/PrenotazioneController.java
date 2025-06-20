package it.epicode.U5W3D5.controller;

import it.epicode.U5W3D5.model.Prenotazione;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/{eventoId}")
    public Prenotazione prenota(@PathVariable int eventoId,
                                @AuthenticationPrincipal Utente utente) {
        return eventoService.prenotaEvento(eventoId, utente);
    }

    @DeleteMapping("/{eventoId}")
    public void annulla(@PathVariable int eventoId,
                        @AuthenticationPrincipal Utente utente) {
        eventoService.annullaPrenotazione(eventoId, utente);
    }

    @GetMapping
    public List<Prenotazione> getPrenotazioniUtente(@AuthenticationPrincipal Utente utente) {
        return eventoService.getPrenotazioniUtente(utente);
    }
}

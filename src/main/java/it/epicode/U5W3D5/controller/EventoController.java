package it.epicode.U5W3D5.controller;

import it.epicode.U5W3D5.dto.EventoDto;
import it.epicode.U5W3D5.model.Evento;
import it.epicode.U5W3D5.model.Prenotazione;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public Evento creaEvento(@RequestBody EventoDto eventoDto,
                             @AuthenticationPrincipal Utente organizzatore) {
        return eventoService.creaEvento(eventoDto, organizzatore);
    }

    @GetMapping
    public List<Evento> getEventiDisponibili() {
        return eventoService.getEventiDisponibili();
    }

    @PutMapping("/{id}")
    public Evento modificaEvento(@PathVariable int id,
                                 @RequestBody EventoDto dto,
                                 @AuthenticationPrincipal Utente utente) {
        return eventoService.modificaEvento(id, dto, utente);
    }

    @DeleteMapping("/{id}")
    public void cancellaEvento(@PathVariable int id,
                               @AuthenticationPrincipal Utente utente) {
        eventoService.cancellaEvento(id, utente);
    }

    @PostMapping("/{id}/prenota")
    public Prenotazione prenotaEvento(@PathVariable int id,
                                      @AuthenticationPrincipal Utente utente) {
        return eventoService.prenotaEvento(id, utente);
    }

    @DeleteMapping("/{id}/prenota")
    public void annullaPrenotazione(@PathVariable int id,
                                    @AuthenticationPrincipal Utente utente) {
        eventoService.annullaPrenotazione(id, utente);
    }

    @GetMapping("/mie-prenotazioni")
    public List<Prenotazione> getPrenotazioniUtente(@AuthenticationPrincipal Utente utente) {
        return eventoService.getPrenotazioniUtente(utente);
    }
}

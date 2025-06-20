package it.epicode.U5W3D5.service;

import it.epicode.U5W3D5.dto.EventoDto;
import it.epicode.U5W3D5.exception.NonTrovatoException;
import it.epicode.U5W3D5.exception.UnAuthorizedException;
import it.epicode.U5W3D5.model.Evento;
import it.epicode.U5W3D5.model.Prenotazione;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.repository.EventoRepository;
import it.epicode.U5W3D5.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Evento creaEvento(EventoDto eventoDto, Utente organizzatore) {
        if (!organizzatore.getRole().name().equals("ORGANIZZATORE")) {
            throw new UnAuthorizedException("Solo un organizzatore può creare eventi");
        }

        Evento evento = new Evento();
        evento.setTitolo(eventoDto.getTitolo());
        evento.setDescrizione(eventoDto.getDescrizione());
        evento.setData(eventoDto.getData());
        evento.setLuogo(eventoDto.getLuogo());
        evento.setPostiDisponibili(eventoDto.getPostiDisponibili());
        evento.setOrganizzatore(organizzatore);

        return eventoRepository.save(evento);
    }

    public List<Evento> getEventiDisponibili() {
        return eventoRepository.findByPostiDisponibiliGreaterThan(0);
    }

    public Prenotazione prenotaEvento(int idEvento, Utente utente) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new NonTrovatoException("Evento non trovato"));

        if (evento.getPostiDisponibili() <= 0) {
            throw new IllegalStateException("Evento sold out");
        }

        boolean giàPrenotato = prenotazioneRepository.existsByEventoAndUtente(evento, utente);
        if (giàPrenotato) {
            throw new IllegalStateException("Hai già prenotato questo evento");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setDataPrenotazione(LocalDateTime.now());
        prenotazioneRepository.save(prenotazione);

        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);

        return prenotazione;
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

    public void annullaPrenotazione(int idEvento, Utente utente) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new NonTrovatoException("Evento non trovato"));

        Prenotazione prenotazione = prenotazioneRepository.findByEventoAndUtente(evento, utente)
                .orElseThrow(() -> new NonTrovatoException("Prenotazione non trovata"));

        prenotazioneRepository.delete(prenotazione);

        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        eventoRepository.save(evento);
    }

    public Evento modificaEvento(int idEvento, EventoDto dto, Utente utente) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new NonTrovatoException("Evento non trovato"));

        if (evento.getOrganizzatore().getId() != utente.getId()) {
            throw new UnAuthorizedException("Non sei l'organizzatore di questo evento");
        }

        evento.setTitolo(dto.getTitolo());
        evento.setDescrizione(dto.getDescrizione());
        evento.setData(dto.getData());
        evento.setLuogo(dto.getLuogo());
        evento.setPostiDisponibili(dto.getPostiDisponibili());

        return eventoRepository.save(evento);
    }

    public void cancellaEvento(int idEvento, Utente utente) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new NonTrovatoException("Evento non trovato"));

        if (evento.getOrganizzatore().getId() != utente.getId()) {
            throw new UnAuthorizedException("Non sei l'organizzatore di questo evento");
        }

        eventoRepository.delete(evento);
    }
}

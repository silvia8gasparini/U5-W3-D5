package it.epicode.U5W3D5.repository;

import it.epicode.U5W3D5.model.Evento;
import it.epicode.U5W3D5.model.Prenotazione;
import it.epicode.U5W3D5.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    boolean existsByEventoAndUtente(Evento evento, Utente utente);
    Optional<Prenotazione> findByEventoAndUtente(Evento evento, Utente utente);
    List<Prenotazione> findByUtente(Utente utente);
}

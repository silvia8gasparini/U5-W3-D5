package it.epicode.U5W3D5.repository;

import it.epicode.U5W3D5.model.Evento;
import it.epicode.U5W3D5.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByPostiDisponibiliGreaterThan(int posti);
    List<Evento> findByOrganizzatore(Utente organizzatore);
}
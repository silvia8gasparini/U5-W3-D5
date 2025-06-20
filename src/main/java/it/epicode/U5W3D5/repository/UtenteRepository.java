package it.epicode.U5W3D5.repository;

import it.epicode.U5W3D5.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    public Optional<Utente> findByEmail(String email);
}

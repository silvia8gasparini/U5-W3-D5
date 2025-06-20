package it.epicode.U5W3D5.service;

import it.epicode.U5W3D5.dto.UtenteDto;
import it.epicode.U5W3D5.enumeration.Role;
import it.epicode.U5W3D5.exception.NonTrovatoException;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente saveUser(UtenteDto userDto) throws NonTrovatoException {

        Utente utente = new Utente();

        utente.setNome(userDto.getNome());
        utente.setCognome(userDto.getCognome());
        utente.setEmail(userDto.getEmail());
        utente.setRole(userDto.getRole());
        utente.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(utente);
    }

    public List<Utente> getUtenti(){

        return userRepository.findAll();
    }

    public Utente getUtente(int id) throws NonTrovatoException {
        return userRepository.findById(id).
                orElseThrow(() -> new NonTrovatoException("User con id:" + id + " non trovato"));
    }

    public Utente updateUtente(int id, UtenteDto utenteDto) throws NonTrovatoException {
        Utente utenteDaAggiornare = getUtente(id);

        utenteDaAggiornare.setNome(utenteDto.getNome());
        utenteDaAggiornare.setCognome(utenteDto.getCognome());
        utenteDaAggiornare.setEmail(utenteDto.getEmail());

        if(!passwordEncoder.matches(utenteDto.getPassword(),utenteDaAggiornare.getPassword()))
            utenteDaAggiornare.setPassword(passwordEncoder.encode(utenteDto.getPassword()));

        return userRepository.save(utenteDaAggiornare);
    }


    public void deleteUtente(int id) throws NonTrovatoException {
        Utente userDaRimuovere = getUtente(id);

        userRepository.delete(userDaRimuovere);
    }
}

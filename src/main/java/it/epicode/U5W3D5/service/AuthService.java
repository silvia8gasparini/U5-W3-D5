package it.epicode.U5W3D5.service;

import it.epicode.U5W3D5.dto.LoginDto;
import it.epicode.U5W3D5.exception.NonTrovatoException;
import it.epicode.U5W3D5.model.Utente;
import it.epicode.U5W3D5.repository.UtenteRepository;
import it.epicode.U5W3D5.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) throws NonTrovatoException {
        Utente utente = utenteRepository.findByEmail(loginDto.getEmail()).
                orElseThrow(() -> new NonTrovatoException("Email/password non trovati"));

        if(passwordEncoder.matches(loginDto.getPassword(),utente.getPassword())){
            return jwtTool.createToken(utente);
        }
        else{
            throw new NonTrovatoException("Email/password non trovati");
        }
    }
}

package it.epicode.U5W3D5.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private Evento evento;

    private String note;
    private LocalDateTime dataPrenotazione;
}
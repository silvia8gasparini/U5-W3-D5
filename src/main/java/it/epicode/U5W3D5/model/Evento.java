package it.epicode.U5W3D5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    private Utente organizzatore;

    @OneToMany (mappedBy = "evento")
    @JsonIgnore
    private List<Prenotazione> Prenotazioni;

}

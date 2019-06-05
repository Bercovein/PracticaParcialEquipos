package com.example.SimulacroParcial.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jugador {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Integer id;
    public String nombre;
    public LocalDateTime nacimiento;
    public Integer numero;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "equipo_id", referencedColumnName = "id")
    @JsonIgnore
    public Equipo equipo;

    @PrePersist
    public void setTime() {
        if (isNull(this.getNacimiento())){
            this.nacimiento = LocalDateTime.now();
        }
    }
}

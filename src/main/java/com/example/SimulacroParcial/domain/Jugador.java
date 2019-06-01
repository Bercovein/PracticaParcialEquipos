package com.example.SimulacroParcial.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Data //getter, setter, equals, hashcode, tostring etc..
@AllArgsConstructor //genera un constructor con todos los argumentos
@NoArgsConstructor //genera un constructor sin argumentos
public class Jugador {

    @Id //id como primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY) //auto_increment
    public Integer id;
    public String nombre;
    public LocalDateTime nacimiento;
    public Integer numero;
    @ManyToOne (fetch = FetchType.LAZY) //matchea la base de datos
    @JoinColumn (name = "equipo_id", referencedColumnName = "id")
    //toma la id del equipo y la pone como foreign key
    @JsonIgnore //ignora al equipo al armar el json para mostrar
    public Equipo equipo;

    @PrePersist //se ejecuta antes de que se persista en la base de datos
    public void setTime() {
        if (isNull(this.getNacimiento())){
            this.nacimiento = LocalDateTime.now();
        }
    }
}

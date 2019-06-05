package com.example.SimulacroParcial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data //getter setter, equals, hashcode etc
@AllArgsConstructor //constructor con argumentos
@NoArgsConstructor //genera un constructor sin argumentos
@Entity //genera una tabla en la db
@Table (name  = "equipos") //le da nombre a la tabla
public class Equipo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //auto_increment
    private Integer id;
    private String nombre;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "equipo")
    @ToString.Exclude
    private List<Jugador> jugadores;
}

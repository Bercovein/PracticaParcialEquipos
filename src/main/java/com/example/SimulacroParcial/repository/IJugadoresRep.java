package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJugadoresRep extends JpaRepository <Jugador,Integer> {
}

package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquiposRep extends JpaRepository <Equipo,Integer> {

}

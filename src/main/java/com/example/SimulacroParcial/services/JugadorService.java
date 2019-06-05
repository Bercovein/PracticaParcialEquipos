package com.example.SimulacroParcial.services;

import com.example.SimulacroParcial.domain.Equipo;
import com.example.SimulacroParcial.domain.Jugador;
import com.example.SimulacroParcial.repository.IEquiposRep;
import com.example.SimulacroParcial.repository.IJugadoresRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class JugadorService {

    private static String EQUIPO_NOT_FOUND = "No existe el equipo con la id: %s";
    private static String JUGADOR_NOT_FOUND = "No existe el equipo con la id: %s";

    @Autowired
    IEquiposRep equiposRepo;
    @Autowired
    IJugadoresRep jugadoresRepo;


    public void addJugador (Jugador jugador, final Integer id){

        Equipo eq = equiposRepo.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(EQUIPO_NOT_FOUND,id)));

        jugador.setEquipo(eq);
        eq.getJugadores().add(jugador);
        jugadoresRepo.save(jugador);
        equiposRepo.save(eq);
    }


    public void updateJugador(Jugador jugador, Integer id){

        Equipo eq = equiposRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(EQUIPO_NOT_FOUND,id)));

        eq.getJugadores().add(jugador);
        jugador.setEquipo(eq);
        jugadoresRepo.save(jugador);
        equiposRepo.save(eq);
    }

    public List<Jugador> getAllJugadores(){
        return jugadoresRepo.findAll();
    }


    public Jugador getJugadorById(final Integer id){
        return jugadoresRepo.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,String.format(JUGADOR_NOT_FOUND,id)));
    }

    public void deleteJugador(final Integer id){
        Jugador jug = jugadoresRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(JUGADOR_NOT_FOUND,id)));

        jugadoresRepo.delete(jug);
    }
}

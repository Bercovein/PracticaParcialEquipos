package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Equipo;
import com.example.SimulacroParcial.domain.Jugador;
import com.example.SimulacroParcial.repository.IEquiposRep;
import com.example.SimulacroParcial.repository.IJugadoresRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/jugadores")
@RestController
public class ControllerJugadores {

    private static String EQUIPO_NOT_FOUND = "No existe el equipo con la id: %s";
    private static String JUGADOR_NOT_FOUND = "No existe el equipo con la id: %s";

    @Autowired
    IEquiposRep equiposRepo;
    @Autowired
    IJugadoresRep jugadoresRepo;

    @PostMapping("/{id}")
    public void addJugador ( @RequestBody Jugador jugador, @PathVariable("id") final Integer id){

        Equipo eq = equiposRepo.findById(id).orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(EQUIPO_NOT_FOUND,id)));

        jugador.setEquipo(eq);
        eq.getJugadores().add(jugador);
        jugadoresRepo.save(jugador);
        equiposRepo.save(eq);
    }

    @PutMapping("/{id}")
    public void updateJugador(@RequestBody Jugador jugador, @PathVariable("id") Integer id){

        Equipo eq = equiposRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(EQUIPO_NOT_FOUND,id)));

        eq.getJugadores().add(jugador);
        jugador.setEquipo(eq);
        jugadoresRepo.save(jugador);
        equiposRepo.save(eq);
    }

    @GetMapping("")

    public List<Jugador> getAllJugadores(){
        return jugadoresRepo.findAll();
    }

    @GetMapping("{id}")
    public Jugador getJugadorById(@PathVariable("id") final Integer id){
        return jugadoresRepo.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST,String.format(JUGADOR_NOT_FOUND,id)));
    }

    @DeleteMapping("{id}")
    public void deleteJugador(@PathVariable("id") final Integer id){
        Jugador jug = jugadoresRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(JUGADOR_NOT_FOUND,id)));

        jugadoresRepo.delete(jug);
    }

}

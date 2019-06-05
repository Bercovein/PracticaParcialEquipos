package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Equipo;
import com.example.SimulacroParcial.domain.Jugador;
import com.example.SimulacroParcial.repository.IEquiposRep;
import com.example.SimulacroParcial.repository.IJugadoresRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
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

@RequestMapping("/equipos")
@RestController
public class ControllerEquipos {


    private static String EQUIPO_NOT_FOUND = "No existe el equipo con la id: %s";
    private static String JUGADOR_NOT_FOUND = "No existe el equipo con la id: %s";

    @Autowired
    IEquiposRep equiposRepo;

    @PostMapping("")
    public void addEquipo(@RequestBody Equipo equipo){
        equiposRepo.save(equipo);
    }

    @GetMapping ("")
    public List<Equipo> getAllEquipos(){
        return equiposRepo.findAll();
    }

    @GetMapping ("/{id}")
    public Equipo getById(@PathVariable("id") final Integer id){
        return equiposRepo.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(EQUIPO_NOT_FOUND,id)));
    }

    @PutMapping("")
    public void updateEquipo(@RequestBody Equipo equipo){

        Equipo eq = equiposRepo.findById(equipo.getId()).orElseThrow(()->new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(EQUIPO_NOT_FOUND,equipo.getId())));

        if(!eq.equals(equipo)){
            equiposRepo.save(equipo);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEquipo(@PathVariable("id") final Integer id){
        Equipo eq = equiposRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.BAD_REQUEST,String.format(EQUIPO_NOT_FOUND,id)));

        equiposRepo.delete(eq);
    }
}

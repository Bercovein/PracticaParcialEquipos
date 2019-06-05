package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Equipo;
import com.example.SimulacroParcial.domain.Jugador;
import com.example.SimulacroParcial.repository.IEquiposRep;
import com.example.SimulacroParcial.repository.IJugadoresRep;
import com.example.SimulacroParcial.services.EquipoService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    EquipoService equiposService;

    @PostMapping("")
    public void addEquipo(@RequestBody Equipo equipo){
        equiposService.addEquipo(equipo);
    }

    @GetMapping ("")
    public List<Equipo> getAllEquipos(){
        return equiposService.getAllEquipos();
    }

    @GetMapping ("/{id}")
    public Equipo getById(@PathVariable("id") final Integer id){
        return equiposService.getById(id);
    }

    @PutMapping("")
    public void updateEquipo(@RequestBody Equipo equipo){

        equiposService.updateEquipo(equipo);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipo(@PathVariable("id") final Integer id){
        equiposService.deleteEquipo(id);
    }
}

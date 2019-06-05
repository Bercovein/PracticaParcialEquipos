package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.DTO.JugadorDTO;
import com.example.SimulacroParcial.domain.Jugador;
import com.example.SimulacroParcial.repository.IJugadoresRep;
import com.example.SimulacroParcial.services.JugadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/jugadores")
@RestController
public class ControllerJugadores {

    @Autowired
    JugadorService jugadorService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/{id}")
    public void addJugador ( @RequestBody Jugador jugador, @PathVariable("id") final Integer id){

        jugadorService.addJugador(jugador,id);
    }

    @PutMapping("/{id}")
    public void updateJugador(@RequestBody Jugador jugador, @PathVariable("id") Integer id){

        jugadorService.updateJugador(jugador,id);
    }

    @GetMapping("")
    public List<JugadorDTO> getAllJugadores(){

        List<Jugador> jugadores = jugadorService.getAllJugadores();

        return jugadores.stream().map(player -> convertEntityToDto(player)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public JugadorDTO getJugadorById(@PathVariable("id") final Integer id){

        Jugador player = jugadorService.getJugadorById(id);

        return convertEntityToDto(player);
    }

    @DeleteMapping("{id}")
    public void deleteJugador(@PathVariable("id") final Integer id){
        jugadorService.deleteJugador(id);
    }

    private JugadorDTO convertEntityToDto( Jugador player)  {
        return modelMapper.map(player, JugadorDTO.class);
    }

    private Jugador convertDtoToEntity(JugadorDTO playerDto){

        return modelMapper.map(playerDto,Jugador.class);
    }

}

package com.example.SimulacroParcial.services;

import com.example.SimulacroParcial.domain.Equipo;
import com.example.SimulacroParcial.repository.IEquiposRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class EquipoService {

    private static String EQUIPO_NOT_FOUND = "No existe el equipo con la id: %s";

    @Autowired
    IEquiposRep equiposRepo;

    public void addEquipo(Equipo equipo){
        equiposRepo.save(equipo);
    }

    public List<Equipo> getAllEquipos(){
        return equiposRepo.findAll();
    }

    public Equipo getById(final Integer id){
        return equiposRepo.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(EQUIPO_NOT_FOUND,id)));
    }

    public void updateEquipo(Equipo equipo){

        Equipo eq = equiposRepo.findById(equipo.getId()).orElseThrow(()->new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(EQUIPO_NOT_FOUND,equipo.getId())));

        if(!eq.equals(equipo)){
            equiposRepo.save(equipo);
        }
    }

    public void deleteEquipo(final Integer id){
        Equipo eq = equiposRepo.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.BAD_REQUEST,String.format(EQUIPO_NOT_FOUND,id)));

        equiposRepo.delete(eq);
    }
}

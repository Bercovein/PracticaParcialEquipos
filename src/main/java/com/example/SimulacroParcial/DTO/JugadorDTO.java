package com.example.SimulacroParcial.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDTO {

    private Integer id;
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "el nacimiento no puede ser nulo")
    private LocalDateTime nacimiento;
    @Max(value = 99, message = "debe ser menor a 99")
    @Positive
    private Integer numero;


}

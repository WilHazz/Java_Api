package org.chany.Model.Dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
/*
Con JsonAutoDetect mas la dependencia Jackson que esta en pom
hago que mis objetos sean visibles al usar json, esto va mas enfocado para LocalDatetime ya que si no uso
 @JsonAutoDetect puede generar un error que dice que no reconoce algunos objetos en la BD
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClienteDto implements Serializable {


    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //muestro el formato para que Json reconozca
    private LocalDateTime fechaRegistro;
}

package org.chany.Model.Dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto implements Serializable {


    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDateTime fechaRegistro;
}

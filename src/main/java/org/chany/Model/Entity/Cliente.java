package org.chany.Model.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "clientes")
/*
Con JsonAutoDetect mas la dependencia Jackson que esta en pom
hago que mis objetos sean visibles al usar json, esto va mas enfocado para LocalDatetime ya que si no uso
 @JsonAutoDetect puede generar un error que dice que no reconoce algunos objetos en la BD
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
   // @JsonProperty("nombre")
    private String nombre;
   // @JsonProperty("apellido")
    private String apellido;
   // @JsonProperty("correo")
    private String correo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //muestro el formato para que Json reconozca
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}

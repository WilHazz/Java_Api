package org.chany.Controller;

import org.chany.Model.Dto.ClienteDto;
import org.chany.Model.Entity.Cliente;
import org.chany.Service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED) //sirve para manejar el estado HTTP en este caso es un 201
    public ClienteDto create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = clienteService.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombre())
                .apellido(clienteSave.getApellido())
                .correo(clienteSave.getCorreo())
                .fechaRegistro((clienteSave.getFechaRegistro()))
                .build();


    }
    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED) //sirve para manejar el estado HTTP en este caso es un 201
    public ClienteDto update(@RequestBody ClienteDto clienteDto) {
        Cliente clienteUpdate = clienteService.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .correo(clienteUpdate.getCorreo())
                .fechaRegistro((clienteUpdate.getFechaRegistro()))
                .build();
    }

    @DeleteMapping("cliente/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try{
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            response.put("mensaje", exDt.getMessage());
            response.put("cliente", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK) // status 200
    public ResponseEntity<ClienteDto>  showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        if (cliente != null) {
            return ResponseEntity.ok(
                ClienteDto.builder()
                    .idCliente(cliente.getIdCliente())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .correo(cliente.getCorreo())
                    .fechaRegistro((cliente.getFechaRegistro()))
                    .build()
                    );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package org.chany.Controller;

import org.chany.Model.Dto.ClienteDto;
import org.chany.Model.Entity.Cliente;
import org.chany.Model.payload.MensajeResponse;
import org.chany.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("cliente/{id}")
    public ResponseEntity<?>  showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        if(cliente == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intentas busacr, no existe!!")
                            .object(null)
                            .build(), HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Exitosamente!!")
                            .object(ClienteDto.builder()
                                    .idCliente(cliente.getIdCliente())
                                    .nombre(cliente.getNombre())
                                    .apellido(cliente.getApellido())
                                    .correo(cliente.getCorreo())
                                    .fechaRegistro((cliente.getFechaRegistro()))
                                    .build())
                            .build(), HttpStatus.OK);
    }

    @PostMapping("cliente")
    //@ResponseStatus(HttpStatus.CREATED) //sirve para manejar el estado HTTP en este caso es un 201
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
         clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteSave.getIdCliente())
                            .nombre(clienteSave.getNombre())
                            .apellido(clienteSave.getApellido())
                            .correo(clienteSave.getCorreo())
                            .fechaRegistro((clienteSave.getFechaRegistro()))
                            .build())
                    .build()
                    , HttpStatus.CREATED );
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
        Cliente clienteUpdate = null;
        try {
            if(clienteService.existsById(id)){
                clienteDto.setIdCliente(id);
            clienteUpdate = clienteService.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteUpdate.getIdCliente())
                            .nombre(clienteUpdate.getNombre())
                            .apellido(clienteUpdate.getApellido())
                            .correo(clienteUpdate.getCorreo())
                            .fechaRegistro((clienteUpdate.getFechaRegistro()))
                            .build())
                    .build()
                    , HttpStatus.CREATED );
            }else{
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intentan actualizar no se encuenttra en la Base de Datos.")
                                .object(null)
                                .build(), HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        //Map<String, Object> response = new HashMap<>();
        try{
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje(exDt.getMessage())
                        .object(null)
                        .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("clientes")
    public ResponseEntity<?>  showAll(){
        List<Cliente> getList = clienteService.ListAll();

        if(getList == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Exitosamente!!")
                            .object(getList)
                            .build(), HttpStatus.OK);
    }
}

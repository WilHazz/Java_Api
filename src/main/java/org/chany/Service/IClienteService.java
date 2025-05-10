package org.chany.Service;

import org.chany.Model.Dto.ClienteDto;
import org.chany.Model.Entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> ListAll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}

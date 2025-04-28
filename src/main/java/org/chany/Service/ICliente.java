package org.chany.Service;

import org.chany.Model.Dto.ClienteDto;
import org.chany.Model.Entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}

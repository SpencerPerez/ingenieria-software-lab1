package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.ErroresModel;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.utilies.Ayuda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public  ClientService(ClientRepository clientRepository1){
        this.clientRepository=clientRepository1;
    }

    public List<Client> getTodosClientes() {
        return (List<Client>) this.clientRepository.findAll();
    }

    public Client getNitCliente (String Nit){
        List<Client> clients=getTodosClientes();
        return clients.stream()
                .filter(client -> Objects.equals(client.getNit(),Nit))
                .findFirst()
                .orElse(new Client());
    }

    public List<Client> getClienteNombreApellido(String NombreYApellido){
        List<Client> clients = getTodosClientes();
        List<Client> clientList= new ArrayList<>();
        for (Client temp: clients){
            if (temp.getFirstName().matches(NombreYApellido)||temp.getLastName().matches(NombreYApellido)){
                clientList.add(temp);
            }
        }
        return clientList;
    }

    public Client getClienteId(long id) {
        return this.clientRepository.findById(id).get();
    }

    public Object crearCliente(Client client) {
        String msg = "Unicamente se pueden registrar si es Mayor de edad >18 anios en adelante";
        if(!Ayuda.finIgualNAnios(client.getBirth(), 18)){
            try {
                throw new Exception(msg);
            } catch (Exception e) {
                e.printStackTrace();
                return new ErroresModel("Menor de edad >18 anios", msg);
            }
        } else if (!(client.getNit().matches("\\d+") && client.getNit().length() == 10)) {
            msg = "NIT No Valido.";
            try {
                throw new Exception(msg);
            } catch (Exception e) {
                e.printStackTrace();
                return new ErroresModel(msg, "Nit contiene caracteres invalidos o es de diferente longitud a 10 caracteres");
            }
        } else {
            client.setFirstName(
                    client.getFirstName().substring(0, 1).toUpperCase() + client.getFirstName().substring(1).toLowerCase()
            );
            client.setLastName(
                    client.getLastName().substring(0, 1).toUpperCase() + client.getLastName().substring(1).toLowerCase()
            );
            return this.clientRepository.save(client);
        }
    }

    public Client registrarCliente(Client client)
    {
        return this.clientRepository.save(client);
    }
}
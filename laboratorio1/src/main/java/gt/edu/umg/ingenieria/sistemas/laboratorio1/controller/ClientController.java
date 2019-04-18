package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ReportService _reportService;
    private final ClientService _clientService;

    @Autowired
    public ClientController(ReportService reportService, ClientService clientService) {
        this._reportService = reportService;
        this._clientService = clientService;
    }

    @GetMapping("/getById")
    public Client getById(@RequestParam(name = "id") long id) {
        return this._clientService.getClienteId(id);
    }

    @GetMapping("/buscarPorNit")
    public Client getByNit(@RequestParam(name = "nit") String nit) {
        return this._clientService.getNitCliente(nit);
    }

    @GetMapping("/buscarPorNombreApellido")
    public List<Client> getByNameAndLastName(@RequestParam(name = "query") String nameAndLastName) {
        return this._clientService.getClienteNombreApellido(nameAndLastName);
    }

    @GetMapping("/buscarTodos")
    private List<Client> getByAll() {
        return this._clientService.getTodosClientes();
    }

    @PostMapping("/crearCliente")
    public Object create(@RequestBody Client client) {
        return this._clientService.crearCliente(client);
    }

    @PutMapping("/editarCliente/{id}/{nit}")
    public Client updateNit(@PathVariable long id, @PathVariable String nit) {
        Client client = getById(id);
        client.setNit(nit);
        return this._clientService.registrarCliente(client);
    }

    @PutMapping("/editarCliente/{id}/{name}/{lastName}")
    public Client updateNameAndLastName(@PathVariable long id, @PathVariable String name, @PathVariable String lastName) {
        Client client = getById(id);
        client.setFirstName(name);
        client.setLastName(lastName);
        return this._clientService.registrarCliente(client);
    }

    @GetMapping("/generarReporteClientes")
    public String generateReport() {
        return this._reportService.generarInforme();
    }
}

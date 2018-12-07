package br.edu.ifpb.apiloopis.rest;

import br.edu.ifpb.apiloopis.entities.Evento;
import br.edu.ifpb.apiloopis.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/eventos")
@CrossOrigin(origins = "http://localhost:8100")
public class EventoController {

    @Autowired
    private EventoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Evento evento) {
        service.salvar(evento);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Evento>> buscar() {
        return ResponseEntity.ok().body(service.buscar());
    }

    @PutMapping
    public ResponseEntity<Evento> atualizar(@RequestBody Evento evento) {
        return ResponseEntity.ok().body(service.atualizar(evento));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Evento> buscarPorEmail(@PathVariable int id) {
        Optional<Evento> p = service.buscarPorId(id);
        return (p.isPresent()) ? ResponseEntity.ok().body(p.get()) : ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}

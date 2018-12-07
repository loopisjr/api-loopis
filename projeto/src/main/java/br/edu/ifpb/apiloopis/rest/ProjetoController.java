package br.edu.ifpb.apiloopis.rest;

import br.edu.ifpb.apiloopis.entities.Projeto;
import br.edu.ifpb.apiloopis.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/projetos")
@CrossOrigin(origins = "http://localhost:8100")
public class ProjetoController {
    
    @Autowired
    private ProjetoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Projeto projeto) {
        service.salvar(projeto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> buscar() {
        return ResponseEntity.ok().body(service.buscar());
    }

    @PutMapping
    public ResponseEntity<Projeto> atualizar(@RequestBody Projeto projeto) {
        return ResponseEntity.ok().body(service.atualizar(projeto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Projeto> buscarPorEmail(@PathVariable int id) {
        Optional<Projeto> p = service.buscarPorId(id);
        return (p.isPresent()) ? ResponseEntity.ok().body(p.get()) : ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}

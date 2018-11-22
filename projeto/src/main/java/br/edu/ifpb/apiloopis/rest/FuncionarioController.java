package br.edu.ifpb.apiloopis.rest;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Funcionario funcionario) {
        service.salvar(funcionario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> buscar() {
        return ResponseEntity.ok().body(service.buscar());
    }

    @PutMapping
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario) {
        return ResponseEntity.ok().body(service.atualizar(funcionario));
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Funcionario> buscarPorEmail(@PathVariable String email) {
        Optional<Funcionario> f = service.buscarPorEmail(email);
        return (f.isPresent()) ? ResponseEntity.ok().body(f.get()) : ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deletar(@PathVariable String email) {
        service.deletar(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Funcionario> login(@RequestBody Funcionario funcionario){
        Funcionario buscado = service.login(funcionario);
        return (buscado == null)?ResponseEntity.noContent().build(): ResponseEntity.ok(buscado);
    }
}

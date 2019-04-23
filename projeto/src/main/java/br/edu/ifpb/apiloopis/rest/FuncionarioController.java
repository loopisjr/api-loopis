package br.edu.ifpb.apiloopis.rest;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.enums.TipoFuncionario;
import br.edu.ifpb.apiloopis.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcionarios")
@CrossOrigin(origins = "http://localhost:8100")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Funcionario funcionario) {
        if(service.salvar(funcionario)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(202).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> buscar() {
        return ResponseEntity.ok().body(service.buscar());
    }

    @PutMapping
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario) {
        return ResponseEntity.ok().body(service.atualizar(funcionario));
    }

    @PutMapping(value = "/updateTipo/{email}")
    public ResponseEntity<Void> atualizarTipo(@PathVariable String email,@RequestBody String tipo){
        service.atualizarTipo(email,tipo.equals("ROOT")? TipoFuncionario.ROOT : TipoFuncionario.NORMAL);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateCargo/{email}")
    public ResponseEntity<Void> atualizarCargo(@PathVariable String email,@RequestBody String cargo){
        service.atualizarCargo(email,cargo);
        return ResponseEntity.ok().build();
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

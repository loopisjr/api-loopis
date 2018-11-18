package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public void salvar(Funcionario funcionario){
        repository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorEmail(String email){
        return repository.findById(email);
    }

    public List<Funcionario> buscar(){
        return repository.findAll();
    }

    public void deletar(String email){
        repository.delete(buscarPorEmail(email).get());
    }

    public Funcionario atualizar(Funcionario funcionario){
        if(buscarPorEmail(funcionario.getEmail()).isPresent()){
            return repository.save(funcionario);
        }
        return null;
    }
}

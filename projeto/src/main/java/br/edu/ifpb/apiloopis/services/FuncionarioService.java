package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.repositories.FuncionarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public void salvar(Funcionario funcionario){
        funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
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

    public Funcionario login(Funcionario funcionario){
        Funcionario buscado = buscarPorEmail(funcionario.getEmail()).get();
        String senha = DigestUtils.md5Hex(funcionario.getSenha());
        if(buscado != null && buscado.getSenha().equals(senha)){
            return buscado;
        }
        return null;
    }
}
package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.entities.Projeto;
import br.edu.ifpb.apiloopis.repositories.ProjetoRepository;
import br.edu.ifpb.apiloopis.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    public void salvar(Projeto projeto){
        repository.save(projeto);
    }

    public Optional<Projeto> buscarPorId(int id){
        return repository.findById(id);
    }

    public List<Projeto> buscar(){
        return repository.findAll();
    }

    public void deletar(int id){
        repository.delete(buscarPorId(id).get());
    }

    public Projeto atualizar(Projeto projeto){
        if(buscarPorId(projeto.getId()).isPresent()){
            return repository.save(projeto);
        }
        return null;
    }

    public Projeto adicionarFuncionarios(int id, List<Funcionario> funcionarios){
        Optional<Projeto> projeto = buscarPorId(id);
        if(projeto.isPresent()){
            projeto.get().setFuncionarios(funcionarios);
            repository.save(projeto.get());
            return projeto.get();
        }
        return null;
    }

}

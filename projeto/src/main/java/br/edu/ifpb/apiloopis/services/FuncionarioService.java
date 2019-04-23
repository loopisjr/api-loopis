package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import br.edu.ifpb.apiloopis.enums.TipoFuncionario;
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

    public boolean salvar(Funcionario funcionario) {
        if (buscarPorEmail(funcionario.getEmail()).isPresent()) {
            return false;
        } else {
            funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
            repository.save(funcionario);
            return true;
        }
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        return repository.findById(email);
    }

    public List<Funcionario> buscar() {
        return repository.findAll();
    }

    public void deletar(String email) {
        repository.delete(buscarPorEmail(email).get());
    }

    public Funcionario atualizar(Funcionario funcionario) {
        if (buscarPorEmail(funcionario.getEmail()).isPresent()) {
            funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
            return repository.save(funcionario);
        }
        return null;
    }

    public void atualizarTipo(String email, TipoFuncionario tipo){
        Optional<Funcionario> funcionario = buscarPorEmail(email);
        if(funcionario.isPresent()){
            funcionario.get().setTipo(tipo);
            repository.save(funcionario.get());
        }
    }

    public void atualizarCargo(String email, String cargo){
        Optional<Funcionario> funcionario = buscarPorEmail(email);
        if(funcionario.isPresent()){
            funcionario.get().setCargo(cargo);
            repository.save(funcionario.get());
        }
    }

    public Funcionario login(Funcionario funcionario) {
        System.out.println(funcionario.toString());
        Optional<Funcionario> optional = buscarPorEmail(funcionario.getEmail());
        if (optional.isPresent()) {
            Funcionario buscado = optional.get();
            String senha = DigestUtils.md5Hex(funcionario.getSenha());
            if (buscado != null && buscado.getSenha().equals(senha) &&
                    buscado.getTipo().equals(funcionario.getTipo())) {
                return buscado;
            }
        }
        return null;
    }
}

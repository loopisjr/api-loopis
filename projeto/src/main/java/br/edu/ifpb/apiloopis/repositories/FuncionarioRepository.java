package br.edu.ifpb.apiloopis.repositories;

import br.edu.ifpb.apiloopis.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario,String> {


}

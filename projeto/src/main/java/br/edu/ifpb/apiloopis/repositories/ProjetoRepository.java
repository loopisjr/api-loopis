package br.edu.ifpb.apiloopis.repositories;

import br.edu.ifpb.apiloopis.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
}

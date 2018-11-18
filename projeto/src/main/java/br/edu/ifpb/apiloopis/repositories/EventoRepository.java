package br.edu.ifpb.apiloopis.repositories;

import br.edu.ifpb.apiloopis.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository  extends JpaRepository<Evento, Integer> {
}

package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.entities.Evento;
import br.edu.ifpb.apiloopis.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public void salvar(Evento evento){
        repository.save(evento);
    }

    public Optional<Evento> buscarPorId(int id){
        return repository.findById(id);
    }

    public List<Evento> buscar(){
        return repository.findAll();
    }

    public void deletar(int id){
        repository.delete(buscarPorId(id).get());
    }

    public Evento atualizar(Evento evento){
        if(buscarPorId(evento.getId()).isPresent()){
            return repository.save(evento);
        }
        return null;
    }
}

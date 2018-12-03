package br.edu.ifpb.apiloopis.services;

import br.edu.ifpb.apiloopis.emails.GerenciadorEmails;
import br.edu.ifpb.apiloopis.entities.Evento;
import br.edu.ifpb.apiloopis.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public void salvar(Evento evento){
        repository.save(evento);
        GerenciadorEmails gerenciadorEmails =  new GerenciadorEmails();
        gerenciadorEmails.preparaEmail(evento);
    }

    @Transactional
    public Optional<Evento> buscarPorId(int id){
        return repository.findById(id);
    }

    @Transactional
    public List<Evento> buscar(){
        return repository.findAll();
    }

    public void deletar(int id){
        repository.delete(buscarPorId(id).get());
    }

    @Transactional
    public Evento atualizar(Evento evento){
        System.out.println(evento.getDescricao());
        if(buscarPorId(evento.getId()).isPresent()){
            Evento e =  repository.save(evento);
            GerenciadorEmails gerenciadorEmails =  new GerenciadorEmails();
            gerenciadorEmails.preparaEmail(evento);
            return e;
        }
        return null;
    }
}

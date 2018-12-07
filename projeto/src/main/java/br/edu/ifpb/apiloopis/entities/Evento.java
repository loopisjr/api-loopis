package br.edu.ifpb.apiloopis.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {

    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private LocalDate data;
    private String hora;
    private String descricao;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    @JoinTable(name = "Funcionario_evento",
            joinColumns = @JoinColumn(name = "emailFuncionario"),
            inverseJoinColumns = @JoinColumn(name = "idEvento"))
    private List<Funcionario> funcionariosEnvolvidos;

    public Evento() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionariosEnvolvidos() {
        return funcionariosEnvolvidos;
    }

    public void setFuncionariosEnvolvidos(List<Funcionario> funcionariosEnvolvidos) {
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id == evento.id &&
                Objects.equals(titulo, evento.titulo) &&
                Objects.equals(data, evento.data) &&
                Objects.equals(hora, evento.hora) &&
                Objects.equals(descricao, evento.descricao) &&
                Objects.equals(funcionariosEnvolvidos, evento.funcionariosEnvolvidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, data, hora, descricao, funcionariosEnvolvidos);
    }
}



package br.edu.ifpb.apiloopis.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import br.edu.ifpb.apiloopis.enums.TipoFuncionario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario {
    @Id
    private String email;
    private String senha;
    private String nome;
    private String cargo;
    private String perfilGithub;
    private String habilidades;
    private TipoFuncionario tipo;
    @ManyToMany(mappedBy = "funcionarios", cascade = CascadeType.ALL)
    private List<Projeto> projetos;
    @ManyToMany(mappedBy = "funcionariosEnvolvidos",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evento> eventos;

    public Funcionario(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPerfilGithub() {
        return perfilGithub;
    }

    public void setPerfilGithub(String perfilGithub) {
        this.perfilGithub = perfilGithub;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public TipoFuncionario getTipo() {
        return tipo;
    }

    public void setTipo(TipoFuncionario tipo) {
        this.tipo = tipo;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(senha, that.senha) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(cargo, that.cargo) &&
                Objects.equals(perfilGithub, that.perfilGithub) &&
                Objects.equals(habilidades, that.habilidades) &&
                tipo == that.tipo &&
                Objects.equals(projetos, that.projetos) &&
                Objects.equals(eventos, that.eventos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, senha, nome, cargo, perfilGithub, habilidades, tipo, projetos, eventos);
    }


}


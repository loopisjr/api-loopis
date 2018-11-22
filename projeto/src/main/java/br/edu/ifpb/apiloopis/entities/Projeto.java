package br.edu.ifpb.apiloopis.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Projeto {
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private Date dataEntrega;
    private String telefone;
    private String nomeContratante;
    private String emailContratante;
    private String descricao;
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "Funcionario_projeto",
            joinColumns = @JoinColumn(name = "emailFuncionario"),
            inverseJoinColumns = @JoinColumn(name = "idProjeto"))
    private List<Funcionario> funcionarios;


    public Projeto() {}

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

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeContratante() {
        return nomeContratante;
    }

    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    public String getEmailContratante() {
        return emailContratante;
    }

    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return id == projeto.id &&
                Objects.equals(titulo, projeto.titulo) &&
                Objects.equals(dataEntrega, projeto.dataEntrega) &&
                Objects.equals(telefone, projeto.telefone) &&
                Objects.equals(nomeContratante, projeto.nomeContratante) &&
                Objects.equals(emailContratante, projeto.emailContratante) &&
                Objects.equals(descricao, projeto.descricao) &&
                Objects.equals(funcionarios, projeto.funcionarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, dataEntrega, telefone, nomeContratante, emailContratante, descricao, funcionarios);
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataEntrega=" + dataEntrega +
                ", telefone='" + telefone + '\'' +
                ", nomeContratante='" + nomeContratante + '\'' +
                ", emailContratante='" + emailContratante + '\'' +
                ", descricao='" + descricao + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}

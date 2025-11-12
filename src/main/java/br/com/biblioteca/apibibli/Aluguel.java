package br.com.biblioteca.apibibli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "alugar")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.time.LocalDate dataAluguel;
    private String nomeLivro;
    private String numeroTombo;
    private String nomeAluno;
    private String cpfAluno;
    private java.time.LocalDate dataDevolucao;
    private java.time.LocalDate dataNascimento;
    private String nomeAutor;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.time.LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(java.time.LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getNumeroTombo() {
        return numeroTombo;
    }

    public void setNumeroTombo(String numeroTombo) {
        this.numeroTombo = numeroTombo;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public java.time.LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(java.time.LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public java.time.LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.time.LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}

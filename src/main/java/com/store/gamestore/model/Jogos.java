package com.store.gamestore.model;

import jakarta.persistence.*;

@Entity
@Table(name="jogos")
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jogo;

    @Column(name="nome", nullable = false, length = 50)
    private String nome;

    @Column(name="preco", nullable = false)
    private Double preco;

    @Column(name="genero", nullable = false, length = 30)
    private String genero;

    public Long getId_jogo() {
        return id_jogo;
    }

    public void setId_jogo(Long id_jogo) {
        this.id_jogo = id_jogo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Jogos{" + "id_jogo=" + id_jogo + ", nome='" + nome + "', genero='" + genero + "', preco=" + preco + "}";
    }
}

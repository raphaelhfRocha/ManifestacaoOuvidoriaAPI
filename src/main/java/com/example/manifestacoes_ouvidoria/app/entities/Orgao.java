package com.example.manifestacoes_ouvidoria.app.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orgao")
public class Orgao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "CHAR(5)")
    private String sigla;
    @Nullable
    @OneToMany(mappedBy = "orgao")
    private List<Manifestacao> manifestacoes = new ArrayList<>();

    public Orgao() {}

    public Orgao(Long id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Orgao(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Manifestacao> getManifestacoes() {
        return manifestacoes;
    }

    public void setManifestacoes(List<Manifestacao> manifestacoes) {
        this.manifestacoes = manifestacoes;
    }
}

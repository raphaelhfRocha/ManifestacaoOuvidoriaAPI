package com.example.manifestacoes_ouvidoria.app.entities;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private String descricao;
    private StatusSituacao statusSituacaoResultante;
    private Long idManifestacao;
    @OneToMany(mappedBy = "movimentacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manifestacao> manifestacoes;

    public Movimentacao() {}

    public Movimentacao(Long id, LocalDateTime dataHora, String descricao, StatusSituacao statusSituacaoResultante, Long idManifestacao) {
        this.id = id;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.statusSituacaoResultante = statusSituacaoResultante;
        this.idManifestacao = idManifestacao;
    }

    public Movimentacao(LocalDateTime dataHora, String descricao, StatusSituacao statusSituacaoResultante, Long idManifestacao) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.statusSituacaoResultante = statusSituacaoResultante;
        this.idManifestacao = idManifestacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusSituacao getStatusSituacaoResultante() {
        return statusSituacaoResultante;
    }

    public void setStatusSituacaoResultante(StatusSituacao statusSituacaoResultante) {
        this.statusSituacaoResultante = statusSituacaoResultante;
    }

    public List<Manifestacao> getManifestacoes() {
        return manifestacoes;
    }

    public void setManifestacoes(List<Manifestacao> manifestacoes) {
        this.manifestacoes = manifestacoes;
    }

    public Long getIdManifestacao() {
        return idManifestacao;
    }

    public void setIdManifestacao(Long idManifestacao) {
        this.idManifestacao = idManifestacao;
    }
}

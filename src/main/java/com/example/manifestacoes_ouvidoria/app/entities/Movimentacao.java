package com.example.manifestacoes_ouvidoria.app.entities;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private String descricao;
    private StatusSituacao statusSituacaoResultante;
    @ManyToOne
    @JoinColumn(name = "manifestacao_id")
    private Manifestacao manifestacao;

    public Movimentacao() {}

    public Movimentacao(Long id, LocalDateTime dataHora, String descricao, StatusSituacao statusSituacaoResultante) {
        this.id = id;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.statusSituacaoResultante = statusSituacaoResultante;
    }

    public Movimentacao(LocalDateTime dataHora, String descricao, StatusSituacao statusSituacaoResultante) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.statusSituacaoResultante = statusSituacaoResultante;
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

    public Manifestacao getManifestacao() {
        return manifestacao;
    }

    public void setManifestacao(Manifestacao manifestacao) {
        this.manifestacao = manifestacao;
    }
}

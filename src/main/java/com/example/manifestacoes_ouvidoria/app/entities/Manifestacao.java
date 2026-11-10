package com.example.manifestacoes_ouvidoria.app.entities;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;
import com.example.manifestacoes_ouvidoria.app.enums.StatusTipoManifestacao;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name = "manifestacoes")
public class Manifestacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusTipoManifestacao tipoManifestacao;
    private String assunto;
    private String descricao;
    @Nullable
    private String nomeManifestante;
    private LocalDateTime dataRegistro;
    private LocalDateTime dataAtualizacaoRegistro;
    @Enumerated(EnumType.STRING)
    private StatusSituacao situacaoManifestacao;
    @NotNull
    @ManyToOne
    private Orgao orgao;
    @ManyToOne
    @JoinColumn(name = "manifestacao_id")
    private Movimentacao movimentacao;

    public Manifestacao() {}

    public Manifestacao(Long id,
                        StatusTipoManifestacao tipoManifestacao,
                        String assunto,
                        String descricao,
                        @Nullable String nomeManifestante,
                        LocalDateTime dataAtualizacaoRegistro,
                        StatusSituacao situacaoManifestacao,
                        Orgao orgao,
                        Movimentacao movimentacao) {
        this.id = id;
        this.tipoManifestacao = tipoManifestacao;
        this.assunto = assunto;
        this.descricao = descricao;
        this.nomeManifestante = nomeManifestante;
        this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
        this.situacaoManifestacao = situacaoManifestacao;
        this.orgao = orgao;
        this.movimentacao = movimentacao;
    }

    public Manifestacao(StatusTipoManifestacao tipoManifestacao,
                        String assunto,
                        String descricao,
                        @Nullable String nomeManifestante,
                        LocalDateTime dataRegistro,
                        LocalDateTime dataAtualizacaoRegistro,
                        StatusSituacao situacaoManifestacao,
                        Orgao orgao,
                        Movimentacao movimentacao) {
        this.tipoManifestacao = tipoManifestacao;
        this.assunto = assunto;
        this.descricao = descricao;
        this.nomeManifestante = nomeManifestante;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
        this.situacaoManifestacao = situacaoManifestacao;
        this.orgao = orgao;
        this.movimentacao = movimentacao;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusTipoManifestacao getTipoManifestacao() {
        return tipoManifestacao;
    }

    public void setTipoManifestacao(StatusTipoManifestacao tipoManifestacao) {
        this.tipoManifestacao = tipoManifestacao;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Nullable
    public String getNomeManifestante() {
        return nomeManifestante;
    }

    public void setNomeManifestante(@Nullable String nomeManifestante) {
        this.nomeManifestante = nomeManifestante;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getDataAtualizacaoRegistro() {
        return dataAtualizacaoRegistro;
    }

    public void setDataAtualizacaoRegistro(LocalDateTime dataAtualizacaoRegistro) {
        this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
    }

    public StatusSituacao getSituacaoManifestacao() {
        return situacaoManifestacao;
    }

    public void setSituacaoManifestacao(StatusSituacao situacaoManifestacao) {
        this.situacaoManifestacao = situacaoManifestacao;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }
}

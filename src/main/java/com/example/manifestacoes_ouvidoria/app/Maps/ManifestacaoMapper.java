package com.example.manifestacoes_ouvidoria.app.Maps;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ManifestacaoMapper {
    public Manifestacao toEntity(ManifestacaoDTO manifestacaoDTO) {
        Manifestacao manifestacao = new Manifestacao();
        manifestacao.setTipoManifestacao(manifestacaoDTO.tipoManifestacao());
        manifestacao.setAssunto(manifestacaoDTO.assunto());
        manifestacao.setDescricao(manifestacaoDTO.descricao());
        manifestacao.setNomeManifestante(manifestacaoDTO.nomeManifestante());
        manifestacao.setDataRegistro(manifestacaoDTO.dataRegistro());
        manifestacao.setDataAtualizacaoRegistro(LocalDateTime.now());
        manifestacao.setSituacaoManifestacao(manifestacaoDTO.situacaoManifestacao());
        return manifestacao;
    }

    public ManifestacaoDTO toDTO(Manifestacao manifestacao) {
        return new ManifestacaoDTO(
                manifestacao.getId(),
                manifestacao.getTipoManifestacao(),
                manifestacao.getAssunto(),
                manifestacao.getDescricao(),
                manifestacao.getNomeManifestante(),
                manifestacao.getDataRegistro(),
                manifestacao.getDataAtualizacaoRegistro(),
                manifestacao.getSituacaoManifestacao()
        );
    }

    public List<ManifestacaoDTO> toDTOList(List<Manifestacao> manifestacoes) {
        return manifestacoes.stream().map(this::toDTO).toList();
    }
}

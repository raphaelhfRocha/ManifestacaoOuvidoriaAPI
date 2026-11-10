package com.example.manifestacoes_ouvidoria.app.maps;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.interfaces.Mapper;
import com.example.manifestacoes_ouvidoria.app.maps.enums.StatusSituacaoEnumMap;
import com.example.manifestacoes_ouvidoria.app.maps.enums.StatusTipoManifestacaoEnumMap;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ManifestacaoMapper implements Mapper<Manifestacao, ManifestacaoDTO> {

    public Manifestacao toEntity(ManifestacaoDTO manifestacaoDTO) {
        StatusTipoManifestacaoEnumMap statusTipoManifestacao = new StatusTipoManifestacaoEnumMap();
        StatusSituacaoEnumMap statusSituacao = new StatusSituacaoEnumMap();

        return new Manifestacao(
                manifestacaoDTO.id(),
                statusTipoManifestacao.toEnum(manifestacaoDTO.statusTipoManifestacao()),
                manifestacaoDTO.assunto(),
                manifestacaoDTO.descricao(),
                manifestacaoDTO.nomeManifestante(),
                LocalDateTime.now(),
                statusSituacao.toEnum(manifestacaoDTO.statusSituacaoManifestacao()),
                null,
                null
        );
    }

    public ManifestacaoDTO toDTO(Manifestacao manifestacao) {
        StatusTipoManifestacaoEnumMap statusTipoManifestacao = new StatusTipoManifestacaoEnumMap();
        StatusSituacaoEnumMap statusSituacao = new StatusSituacaoEnumMap();

        return new ManifestacaoDTO(
                manifestacao.getId(),
                statusTipoManifestacao.toString(manifestacao.getTipoManifestacao()),
                manifestacao.getAssunto(),
                manifestacao.getDescricao(),
                manifestacao.getNomeManifestante(),
                manifestacao.getDataRegistro(),
                manifestacao.getDataAtualizacaoRegistro(),
                statusSituacao.toString(manifestacao.getSituacaoManifestacao()),
                manifestacao.getOrgao().getSigla()
        );
    }

    public List<ManifestacaoDTO> toDTOList(List<Manifestacao> manifestacoes) {
        return manifestacoes.stream().map(this::toDTO).toList();
    }
}

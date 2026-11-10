package com.example.manifestacoes_ouvidoria.app.maps;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.DTOs.OrgaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import com.example.manifestacoes_ouvidoria.app.interfaces.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgaoMapper implements Mapper<Orgao, OrgaoDTO> {
    private final Mapper<Manifestacao, ManifestacaoDTO> mapperManifestacao;

    public OrgaoMapper(@Lazy Mapper<Manifestacao, ManifestacaoDTO> mapperManifestacao) {
        this.mapperManifestacao = mapperManifestacao;
    }

    @Override
    public Orgao toEntity(OrgaoDTO orgaoDTO) {
        return new Orgao(
                orgaoDTO.id(),
                orgaoDTO.nome(),
                orgaoDTO.sigla()
        );
    }

    @Override
    public OrgaoDTO toDTO(Orgao orgao) {
        return new OrgaoDTO(
                orgao.getId(),
                orgao.getNome(),
                orgao.getSigla(),
                mapperManifestacao.toDTOList(orgao.getManifestacoes())
        );
    }

    @Override
    public List<OrgaoDTO> toDTOList(List<Orgao> orgaos) {
        return orgaos.stream().map(this::toDTO).toList();
    }
}

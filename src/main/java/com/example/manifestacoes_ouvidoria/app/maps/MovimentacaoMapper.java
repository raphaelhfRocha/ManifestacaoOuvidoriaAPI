package com.example.manifestacoes_ouvidoria.app.maps;

import com.example.manifestacoes_ouvidoria.app.DTOs.MovimentacaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Movimentacao;
import com.example.manifestacoes_ouvidoria.app.interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovimentacaoMapper implements Mapper<Movimentacao, MovimentacaoDTO> {

    @Override
    public Movimentacao toEntity(MovimentacaoDTO movimentacaoDto) {
        return new Movimentacao(
                movimentacaoDto.id(),
                movimentacaoDto.dataHora(),
                movimentacaoDto.descricao(),
                movimentacaoDto.statusSituacaoResultante(),
                movimentacaoDto.idManifestacao()
        );
    }

    @Override
    public MovimentacaoDTO toDTO(Movimentacao movimentacaoEntity) {
        return new MovimentacaoDTO(
                movimentacaoEntity.getId(),
                movimentacaoEntity.getDataHora(),
                movimentacaoEntity.getDescricao(),
                movimentacaoEntity.getStatusSituacaoResultante(),
                movimentacaoEntity.getIdManifestacao()
        );
    }

    @Override
    public List<MovimentacaoDTO> toDTOList(List<Movimentacao> movimentacoes) {
        return movimentacoes.stream().map(this::toDTO).toList();
    }
}

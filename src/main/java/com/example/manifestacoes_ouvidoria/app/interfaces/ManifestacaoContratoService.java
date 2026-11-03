package com.example.manifestacoes_ouvidoria.app.interfaces;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;

import java.util.List;

public interface ManifestacaoContratoService {
    List<ManifestacaoDTO> getAllManifestacoes();
    ManifestacaoDTO getManifestacaoById(Long id);
    ManifestacaoDTO registrarManifestacao(ManifestacaoDTO manifestacaoDTO);
    ManifestacaoDTO atualizarManifestacao(Long id, ManifestacaoDTO manifestacaoDTO);
    void excluirManifestacao(Long id);
}

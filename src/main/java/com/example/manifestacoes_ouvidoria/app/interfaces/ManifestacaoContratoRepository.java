package com.example.manifestacoes_ouvidoria.app.interfaces;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;

import java.util.List;

public interface ManifestacaoContratoRepository {
    List<Manifestacao> getAllManifestacoes();
    Manifestacao getManifestacaoById(Long id);
    Manifestacao registrarManifestacao(Manifestacao manifestacao);
    Manifestacao atualizarManifestacao(Manifestacao manifestacao);
    void excluirManifestacao(Long id);
}

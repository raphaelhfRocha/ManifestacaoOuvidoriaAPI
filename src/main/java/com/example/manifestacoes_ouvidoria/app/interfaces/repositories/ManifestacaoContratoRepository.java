package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;

import java.util.List;

public interface ManifestacaoContratoRepository {
    List<Manifestacao> getAllManifestacoes();
    Manifestacao getManifestacaoById(Long id);
    List<Manifestacao> getManifestacoesByOrgao(Orgao orgao);
    Manifestacao registrarManifestacao(Manifestacao manifestacao);
    Manifestacao atualizarManifestacao(Manifestacao manifestacao);
    void excluirManifestacao(Long id);
}

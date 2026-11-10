package com.example.manifestacoes_ouvidoria.app.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.ManifestacaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.ManifestacaoContratoRepositoryJpa;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ManifestacaoRepository implements ManifestacaoContratoRepository {

    private final ManifestacaoContratoRepositoryJpa context;

    public ManifestacaoRepository(ManifestacaoContratoRepositoryJpa context) {
        this.context = context;
    }

    @Override
    public List<Manifestacao> getAllManifestacoes() {
        return context.findAll();
    }

    @Override
    public Manifestacao getManifestacaoById(Long id) {
        return context.findById(id).orElseThrow(() ->
                new RuntimeException("Manifestação não encontrada: " + id));
    }

    public List<Manifestacao> getManifestacoesByOrgao(Orgao orgao) {
        return context.findManifestacoesByOrgao(orgao).orElseThrow(() ->
                new RuntimeException("Manifestação não encontrada: %s".formatted(orgao)));
    }

    @Override
    public Manifestacao registrarManifestacao(Manifestacao manifestacao) {
        return context.save(manifestacao);
    }

    @Override
    public Manifestacao atualizarManifestacao(Manifestacao manifestacao) {
        return context.save(manifestacao);
    }

    @Override
    public void excluirManifestacao(Long id) {
        context.deleteById(id);
    }
}

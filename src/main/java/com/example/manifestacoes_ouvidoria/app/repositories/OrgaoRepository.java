package com.example.manifestacoes_ouvidoria.app.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.OrgaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.OrgaoContratoRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrgaoRepository implements OrgaoContratoRepository {
    private final OrgaoContratoRepositoryJpa context;

    public OrgaoRepository(OrgaoContratoRepositoryJpa context) {
        this.context = context;
    }

    @Override
    public List<Orgao> getAllOrgaos() {
        return context.findAll();
    }

    @Override
    public Orgao getOrgaoById(Long id) {
        return context.findById(id).orElseThrow(() ->
                new RuntimeException("Órgão não encontrado: " + id));
    }

    public Orgao getOrgaoBySigla(String sigla) {
        return context.findOrgaoBySigla(sigla).orElseThrow(() ->
                new RuntimeException("Órgão não encontrado: %s".formatted(sigla)));
    }

    @Override
    public Orgao registrarOrgao(Orgao orgao) {
        return context.save(orgao);
    }

    @Override
    public Orgao atualizarOrgao(Orgao orgao) {
        return context.save(orgao);
    }

    @Override
    public void excluirOrgao(Long id) {
        context.deleteById(id);
    }
}

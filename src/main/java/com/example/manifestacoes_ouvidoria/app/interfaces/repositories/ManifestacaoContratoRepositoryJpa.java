package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManifestacaoContratoRepositoryJpa extends JpaRepository<Manifestacao, Long> {
    Optional<List<Manifestacao>> findManifestacoesByOrgao(Orgao orgao);
}

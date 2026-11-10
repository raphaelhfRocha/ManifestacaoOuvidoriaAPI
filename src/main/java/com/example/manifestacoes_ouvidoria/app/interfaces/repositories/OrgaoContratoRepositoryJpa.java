package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgaoContratoRepositoryJpa extends JpaRepository<Orgao, Long> {
    Optional<Orgao> findOrgaoBySigla(String sigla);
}

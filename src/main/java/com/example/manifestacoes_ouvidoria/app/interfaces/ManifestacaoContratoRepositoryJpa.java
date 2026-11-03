package com.example.manifestacoes_ouvidoria.app.interfaces;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManifestacaoContratoRepositoryJpa extends JpaRepository<Manifestacao, Long> {
}

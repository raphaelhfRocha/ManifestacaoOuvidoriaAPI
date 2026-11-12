package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimentacaoContratoRepositoryJpa extends JpaRepository<Movimentacao, Long> {
}

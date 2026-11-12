package com.example.manifestacoes_ouvidoria.app.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Movimentacao;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.MovimentacaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.MovimentacaoContratoRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class MovimentacaoRepository implements MovimentacaoContratoRepository {
    private final MovimentacaoContratoRepositoryJpa context;

    public MovimentacaoRepository(MovimentacaoContratoRepositoryJpa context) {
        this.context = context;
    }

    @Override
    public Movimentacao registrarMovimentacao(Movimentacao movimentacao) {
        return context.save(movimentacao);
    }

    @Override
    public Movimentacao atualizarMovimentacao(Movimentacao movimentacao) {
        return context.save(movimentacao);
    }
}

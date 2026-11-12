package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;

import com.example.manifestacoes_ouvidoria.app.entities.Movimentacao;

public interface MovimentacaoContratoRepository {
    Movimentacao registrarMovimentacao(Movimentacao movimentacao);
    Movimentacao atualizarMovimentacao(Movimentacao movimentacao);
}

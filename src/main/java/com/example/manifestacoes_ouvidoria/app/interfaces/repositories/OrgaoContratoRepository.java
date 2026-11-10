package com.example.manifestacoes_ouvidoria.app.interfaces.repositories;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import java.util.List;

public interface OrgaoContratoRepository {
    List<Orgao> getAllOrgaos();
    Orgao getOrgaoById(Long id);
    Orgao getOrgaoBySigla(String sigla);
    Orgao registrarOrgao(Orgao orgao);
    Orgao atualizarOrgao(Orgao orgao);
    void excluirOrgao(Long id);
}

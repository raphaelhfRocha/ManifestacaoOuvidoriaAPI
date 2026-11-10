package com.example.manifestacoes_ouvidoria.app.interfaces.services;

import com.example.manifestacoes_ouvidoria.app.DTOs.OrgaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;

import java.util.List;

public interface OrgaoContratoService {
    List<OrgaoDTO> getAllOrgaos();
    OrgaoDTO getOrgaoById(Long id);
    OrgaoDTO registrarOrgao(OrgaoDTO orgaoDTO);
    OrgaoDTO atualizarOrgao(Long id, OrgaoDTO orgaoDTO);
    void excluirOrgao(Long id);
}

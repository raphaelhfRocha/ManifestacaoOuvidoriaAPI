package com.example.manifestacoes_ouvidoria.app.services;

import com.example.manifestacoes_ouvidoria.app.DTOs.OrgaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import com.example.manifestacoes_ouvidoria.app.interfaces.Mapper;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.OrgaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.services.OrgaoContratoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgaoService implements OrgaoContratoService {
    private final OrgaoContratoRepository repository;
    private final Mapper<Orgao, OrgaoDTO> mapper;

    public OrgaoService(OrgaoContratoRepository repository, Mapper<Orgao, OrgaoDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<OrgaoDTO> getAllOrgaos() {
        return mapper.toDTOList(repository.getAllOrgaos());
    }

    @Override
    public OrgaoDTO getOrgaoById(Long id) {
        return mapper.toDTO(repository.getOrgaoById(id));
    }

    @Override
    public OrgaoDTO registrarOrgao(OrgaoDTO orgaoDTO) {
        var orgaoToEntity = mapper.toEntity(orgaoDTO);
        return mapper.toDTO(repository.registrarOrgao(orgaoToEntity));
    }

    @Override
    public OrgaoDTO atualizarOrgao(Long id, OrgaoDTO orgaoDTO) {
        getOrgaoById(id);
        var orgaoToEntity = mapper.toEntity(orgaoDTO);
        orgaoToEntity.setId(id);
        return mapper.toDTO(repository.atualizarOrgao(orgaoToEntity));
    }

    @Override
    public void excluirOrgao(Long id) {
        repository.getOrgaoById(id);
        repository.excluirOrgao(id);
    }
}

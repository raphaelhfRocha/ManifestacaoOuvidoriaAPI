package com.example.manifestacoes_ouvidoria.app.services;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.Maps.ManifestacaoMapper;
import com.example.manifestacoes_ouvidoria.app.interfaces.ManifestacaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.ManifestacaoContratoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManifestacaoService implements ManifestacaoContratoService {
    private final ManifestacaoContratoRepository repository;
    private final ManifestacaoMapper mapper;

    public ManifestacaoService(ManifestacaoContratoRepository repository, ManifestacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ManifestacaoDTO> getAllManifestacoes() {
        return mapper.toDTOList(repository.getAllManifestacoes());
    }

    public ManifestacaoDTO getManifestacaoById(Long id) {
        return mapper.toDTO(repository.getManifestacaoById(id));
    }

    public ManifestacaoDTO registrarManifestacao(ManifestacaoDTO manifestacaoDTO) {
        var manifestacaoToEntity = mapper.toEntity(manifestacaoDTO);
        manifestacaoToEntity.setDataRegistro(LocalDateTime.now());
        repository.registrarManifestacao(manifestacaoToEntity);
        return manifestacaoDTO;
    }

    public ManifestacaoDTO atualizarManifestacao(Long id, ManifestacaoDTO manifestacaoDTO) {
        repository.getManifestacaoById(id);
        var manifestacaoToEntity = mapper.toEntity(manifestacaoDTO);
        manifestacaoToEntity.setId(id);
        repository.atualizarManifestacao(manifestacaoToEntity);
        return manifestacaoDTO;
    }

    public void excluirManifestacao(Long id) {
        repository.getManifestacaoById(id);
        repository.excluirManifestacao(id);
    }
}

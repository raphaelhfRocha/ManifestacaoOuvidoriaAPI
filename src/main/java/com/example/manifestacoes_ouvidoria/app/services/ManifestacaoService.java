package com.example.manifestacoes_ouvidoria.app.services;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.DTOs.MovimentacaoDTO;
import com.example.manifestacoes_ouvidoria.app.DTOs.OrgaoDTO;
import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import com.example.manifestacoes_ouvidoria.app.entities.Movimentacao;
import com.example.manifestacoes_ouvidoria.app.entities.Orgao;
import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;
import com.example.manifestacoes_ouvidoria.app.interfaces.Mapper;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.ManifestacaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.MovimentacaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.repositories.OrgaoContratoRepository;
import com.example.manifestacoes_ouvidoria.app.interfaces.services.ManifestacaoContratoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManifestacaoService implements ManifestacaoContratoService {
    private final ManifestacaoContratoRepository repository;
    private final Mapper<Manifestacao, ManifestacaoDTO> mapper;
    private final OrgaoContratoRepository orgaoRepository;
    private final MovimentacaoContratoRepository movimentacaoRepository;
    private final Mapper<Movimentacao, MovimentacaoDTO> movimentacaoMapper;

    public ManifestacaoService(ManifestacaoContratoRepository repository,
                               Mapper<Manifestacao, ManifestacaoDTO> mapper,
                               OrgaoContratoRepository orgaoRepository,
                               MovimentacaoContratoRepository movimentacaoRepository,
                               Mapper<Movimentacao, MovimentacaoDTO> movimentacaoMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.orgaoRepository = orgaoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
    }

    @Override
    public List<ManifestacaoDTO> getAllManifestacoes() {
        return mapper.toDTOList(repository.getAllManifestacoes());
    }

    @Override
    public ManifestacaoDTO getManifestacaoById(Long id) {
        return mapper.toDTO(repository.getManifestacaoById(id));
    }

    @Override
    public List<ManifestacaoDTO> getManifestacoesByOrgao(String sigla) {
        var orgaoFound = orgaoRepository.getOrgaoBySigla(sigla);
        var manifestacoesFound = repository.getManifestacoesByOrgao(orgaoFound);
        return mapper.toDTOList(manifestacoesFound);
    }

    @Override
    public ManifestacaoDTO registrarManifestacao(ManifestacaoDTO manifestacaoDTO) {
        var orgaoFound = orgaoRepository.getOrgaoBySigla(manifestacaoDTO.orgao());
        var manifestacaoToEntity = mapper.toEntity(manifestacaoDTO);
        manifestacaoToEntity.setSituacaoManifestacao(StatusSituacao.stsAberta);
        manifestacaoToEntity.setDataRegistro(LocalDateTime.now());
        manifestacaoToEntity.setOrgao(orgaoFound);
        var manifestacaoRegistrada = repository.registrarManifestacao(manifestacaoToEntity);

        MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO(
                null,
                manifestacaoRegistrada.getDataRegistro(),
                generateDescription(null, manifestacaoRegistrada.getSituacaoManifestacao()),
                manifestacaoRegistrada.getSituacaoManifestacao(),
                manifestacaoRegistrada.getId()
        );
        movimentacaoRepository.registrarMovimentacao(movimentacaoMapper.
                toEntity(movimentacaoDTO));
        return mapper.toDTO(manifestacaoRegistrada);
    }

    @Override
    public ManifestacaoDTO atualizarManifestacao(Long id, ManifestacaoDTO manifestacaoDTO) {
        var manifestacaoFound = repository.getManifestacaoById(id);
        var orgaoFound = orgaoRepository.getOrgaoBySigla(manifestacaoDTO.orgao());
        var manifestacaoToEntity = mapper.toEntity(manifestacaoDTO);
        manifestacaoToEntity.setId(id);
        manifestacaoToEntity.setDataRegistro(manifestacaoFound.getDataRegistro());
        manifestacaoToEntity.setOrgao(orgaoFound);
        var manifestacaoAtualizada = repository.atualizarManifestacao(manifestacaoToEntity);

        var movimentacaoAlterar = new MovimentacaoDTO(
                null,
                manifestacaoAtualizada.getDataAtualizacaoRegistro(),
                generateDescription(manifestacaoFound.getSituacaoManifestacao(),
                        manifestacaoAtualizada.getSituacaoManifestacao()),
                manifestacaoAtualizada.getSituacaoManifestacao(),
                manifestacaoAtualizada.getId()
        );
        movimentacaoRepository.atualizarMovimentacao(movimentacaoMapper.toEntity(movimentacaoAlterar));
        return mapper.toDTO(manifestacaoAtualizada);
    }

    @Override
    public void excluirManifestacao(Long id) {
        repository.getManifestacaoById(id);
        repository.excluirManifestacao(id);
    }

    private String generateDescription(StatusSituacao statusSituacaoBefore, StatusSituacao statusSituacaoAfter) {
        String descricao = "";
        if (statusSituacaoBefore == null &&
                statusSituacaoAfter == StatusSituacao.stsAberta) {
            descricao = "Situação: aberta";
        }
        else if (statusSituacaoBefore == StatusSituacao.stsAberta &&
                statusSituacaoAfter == StatusSituacao.stsEmAnalise) {
            descricao = "Situação alterada: aberta para em análise";
        } else if (statusSituacaoBefore == StatusSituacao.stsEmAnalise &&
                statusSituacaoAfter == StatusSituacao.stsConcluida) {
            descricao = "Situação alterada: análise para concluída";
        }
        return descricao;
    }
}

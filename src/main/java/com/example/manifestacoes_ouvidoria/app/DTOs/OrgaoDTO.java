package com.example.manifestacoes_ouvidoria.app.DTOs;

import com.example.manifestacoes_ouvidoria.app.entities.Manifestacao;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrgaoDTO(
        @Nullable
            Long id,
        @NotBlank(message = "Nome do órgão obrigatório")
        @Size(max = 50)
            String nome,
        @NotBlank(message = "Sigla do órgão obrigatória")
        @Size(max = 4, min = 3)
            String sigla,
        @Nullable
            List<ManifestacaoDTO> manifestacoesDTO
) {}

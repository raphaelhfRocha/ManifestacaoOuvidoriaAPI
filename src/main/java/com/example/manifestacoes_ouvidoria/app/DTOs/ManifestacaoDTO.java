package com.example.manifestacoes_ouvidoria.app.DTOs;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record ManifestacaoDTO(
        @Nullable
                Long id,
        @NotNull
                String statusTipoManifestacao,
        @NotBlank(message = "O assunto é obrigatório")
        @Size(max = 100)
                String assunto,
        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 1000)
                String descricao,
        @Nullable
                String nomeManifestante,
        @Nullable
        @PastOrPresent
                LocalDateTime dataRegistro,
        @Nullable
        @PastOrPresent
                LocalDateTime dataAtualizacaoRegistro,
        @Nullable
                String statusSituacaoManifestacao,
        @Nullable
                String orgao,
        @Nullable
                List<MovimentacaoDTO> movimentacoesDTO
) {}

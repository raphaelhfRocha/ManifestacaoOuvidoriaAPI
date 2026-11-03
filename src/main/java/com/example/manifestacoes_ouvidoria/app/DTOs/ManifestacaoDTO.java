package com.example.manifestacoes_ouvidoria.app.DTOs;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacaoManifestacao;
import com.example.manifestacoes_ouvidoria.app.enums.StatusTipoManifestacao;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ManifestacaoDTO(
        @Nullable
                Long id,
        @NotNull
                StatusTipoManifestacao tipoManifestacao,
        @NotBlank(message = "O assunto é obrigatório")
        @Size(max = 100)
                String assunto,
        @NotBlank(message = "A descrição é obrigatório")
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
        @NotNull
                StatusSituacaoManifestacao situacaoManifestacao
) {}

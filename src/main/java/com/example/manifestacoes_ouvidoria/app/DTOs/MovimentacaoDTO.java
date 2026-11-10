package com.example.manifestacoes_ouvidoria.app.DTOs;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record MovimentacaoDTO(
   @Nullable
        Long id,
   @PastOrPresent
        LocalDateTime dataHora,
   @Size(max = 1000)
        String descricao,
   @NotNull
   @NotBlank(message = "Status situação resultante obrigatório")
        StatusSituacao statusSituacaoResultante,
   @Nullable
        Long idManifestacao
) {}

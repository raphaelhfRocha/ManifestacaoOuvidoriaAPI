package com.example.manifestacoes_ouvidoria.app.maps.enums;

import com.example.manifestacoes_ouvidoria.app.enums.StatusSituacao;

public class StatusSituacaoEnumMap {
    public String toString(StatusSituacao status) {
        String response = "";
        switch (status) {
            case stsNone:
                response = "";
                break;
            case stsAberta:
                response = "Aberta";
                break;
            case stsEmAnalise:
                response = "Em análise";
                break;
            case stsConcluida:
                response = "Concluída";
                break;
        }
        return response;
    }

    public static StatusSituacao toEnum(String status) {
        StatusSituacao statusEnumResponse = StatusSituacao.stsNone;
        switch (status) {
            case "":
                statusEnumResponse = StatusSituacao.stsNone;
                break;
            case "Ativo":
                statusEnumResponse = StatusSituacao.stsAberta;
                break;
            case "Em análise":
                statusEnumResponse = StatusSituacao.stsEmAnalise;
                break;
            case "Concluída":
                statusEnumResponse = StatusSituacao.stsConcluida;
                break;
        }
        return statusEnumResponse;
    }
}

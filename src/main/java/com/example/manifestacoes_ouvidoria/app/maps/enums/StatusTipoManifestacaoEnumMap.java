package com.example.manifestacoes_ouvidoria.app.maps.enums;
import com.example.manifestacoes_ouvidoria.app.enums.StatusTipoManifestacao;

public class StatusTipoManifestacaoEnumMap {
    public String toString(StatusTipoManifestacao status) {
        String response = "";
        switch (status) {
            case stsNone:
                response = null;
                break;
            case stsDenuncia:
                response = "Denuncia";
                break;
            case stsReclamacao:
                response = "Reclamação";
                break;
            case stsSugestao:
                response = "Sugestão";
                break;
            case stsElogio:
                response = "Elogio";
                break;
        }
        return response;
    }

    public StatusTipoManifestacao toEnum(String status) {
        StatusTipoManifestacao statusEnumResponse = StatusTipoManifestacao.stsNone;
        switch(status) {
            case "Denuncia":
                statusEnumResponse = StatusTipoManifestacao.stsDenuncia;
                break;
            case "Reclamação":
                statusEnumResponse = StatusTipoManifestacao.stsReclamacao;
                break;
            case "Sugestão":
                statusEnumResponse = StatusTipoManifestacao.stsSugestao;
                break;
            case "Elogio":
                statusEnumResponse = StatusTipoManifestacao.stsElogio;
                break;
        }
        return statusEnumResponse;
    }
}

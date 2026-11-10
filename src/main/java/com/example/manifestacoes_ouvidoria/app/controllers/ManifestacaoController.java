package com.example.manifestacoes_ouvidoria.app.controllers;

import com.example.manifestacoes_ouvidoria.app.DTOs.ManifestacaoDTO;
import com.example.manifestacoes_ouvidoria.app.interfaces.services.ManifestacaoContratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/manifestacoes")
public class ManifestacaoController {

    private final ManifestacaoContratoService service;

    public ManifestacaoController(ManifestacaoContratoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ManifestacaoDTO>> getAllManifestacoes() {
        try {
            return ResponseEntity.ok(service.getAllManifestacoes());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManifestacaoDTO> getManifestacaoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getManifestacaoById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
        }
    }

    @GetMapping("/orgao/{sigla}")
    public ResponseEntity<List<ManifestacaoDTO>> getManifestacoesByOrgao(@PathVariable String sigla) {
        try {
            return ResponseEntity.ok(service.getManifestacoesByOrgao(sigla));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: %s".formatted(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ManifestacaoDTO> registrarManifestacao(@Valid @RequestBody ManifestacaoDTO manifestacaoDTO) {
        try {
            var manifestacaoRegistrada = service.registrarManifestacao(manifestacaoDTO);
            URI location = URI.create("/manifestacoes/" + manifestacaoRegistrada.id());
            return ResponseEntity.created(location).body(manifestacaoRegistrada);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManifestacaoDTO> atualizarManifestacao(@PathVariable Long id, @Valid @RequestBody ManifestacaoDTO manifestacaoDTO) {
        try {
            var manifestacaoAtualizada = service.atualizarManifestacao(id, manifestacaoDTO);
            return ResponseEntity.ok(manifestacaoAtualizada);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManifestacaoDTO> excluirManifestacao(@PathVariable Long id) {
        try {
            service.excluirManifestacao(id);
            return ResponseEntity.noContent().build();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: " + e.getMessage());
        }
    }
}

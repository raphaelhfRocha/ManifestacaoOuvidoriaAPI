package com.example.manifestacoes_ouvidoria.app.controllers;

import com.example.manifestacoes_ouvidoria.app.DTOs.OrgaoDTO;
import com.example.manifestacoes_ouvidoria.app.interfaces.services.OrgaoContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/orgaos")
public class OrgaoController {
    private final OrgaoContratoService service;

    public OrgaoController(OrgaoContratoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrgaoDTO>> getAllOrgaos() {
        try {
            return ResponseEntity.ok(service.getAllOrgaos());
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor: %s".formatted(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgaoDTO> getOrgaoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOrgaoById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: %s".formatted(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<OrgaoDTO> registrarOrgao(@RequestBody OrgaoDTO orgaoDTO) {
        try {
            var orgaoRegistrado = service.registrarOrgao(orgaoDTO);
            URI location = URI.create("/orgaos/" + orgaoRegistrado.id());
            return ResponseEntity.created(location).body(orgaoRegistrado);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: %s".formatted(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrgaoDTO> atualizarOrgao(@PathVariable Long id, @RequestBody OrgaoDTO orgaoDTO) {
        try {
            var orgaoAtualizado = service.atualizarOrgao(id, orgaoDTO);
            return ResponseEntity.ok(orgaoAtualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: %s".formatted(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrgaoDTO> excluirOrgao(@PathVariable Long id) {
        try {
            service.excluirOrgao(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor: %s".formatted(e.getMessage()));
        }
    }
}
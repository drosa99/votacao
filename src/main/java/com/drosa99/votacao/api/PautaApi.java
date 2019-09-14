package com.drosa99.votacao.api;

import com.drosa99.votacao.api.request.PautaRequest;
import com.drosa99.votacao.api.response.PautaResponse;
import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.service.PautaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pauta")
public class PautaApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(PautaApi.class);
    private PautaService pautaService;

    public PautaApi(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra uma pauta.")
    public ResponseEntity<PautaEntity> cadastrarConta(@Valid @RequestBody() PautaRequest pautaRequest) {
        LOGGER.info("Iniciado cadastro de nova pauta.");
        PautaEntity pautaEntity = pautaService.cadastrarPauta(pautaRequest);
        LOGGER.info("Finalizada cadastro de nova pauta.");
        return ResponseEntity.ok(pautaEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Contabilizar resultado da pauta.")
    public ResponseEntity<PautaResponse> contabilizarPauta(@PathVariable Long id) {
        LOGGER.info("Iniciada busca de resultados da pauta.");
        PautaResponse pautaResponse = pautaService.contabilizarResultado(id);
        LOGGER.info("Finalizada busca de resultados da pauta.");
        return ResponseEntity.ok(pautaResponse);
    }

    @PutMapping("/iniciar/{id}/minutos/{minutos}")
    @ApiOperation(value = "Iniciar sessao de votacao de uma pauta.")
    public ResponseEntity iniciarVotacao(@PathVariable Long id, @PathVariable Long minutos) {
        LOGGER.info("Iniciada sessao de votacao de uma pauta.");
        pautaService.iniciarVotacao(id, minutos);
        LOGGER.info("Finalizada inicio de sessao de votacao de uma pauta.");
        return ResponseEntity.ok(Void.class);
    }
}

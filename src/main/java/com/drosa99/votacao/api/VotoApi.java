package com.drosa99.votacao.api;

import com.drosa99.votacao.api.request.VotoRequest;
import com.drosa99.votacao.service.VotoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/voto")
public class VotoApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(VotoApi.class);
    private VotoService votoService;

    public VotoApi(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra um voto.")
    public ResponseEntity cadastrarVoto(@Valid @RequestBody() VotoRequest votoRequest) {
        LOGGER.info("Iniciado cadastro de novo voto.");
        votoService.cadastrarVoto(votoRequest);
        LOGGER.info("Finalizada cadastro de nova pauta.");
        return ResponseEntity.ok(Void.class);
    }
}

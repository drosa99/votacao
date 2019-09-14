package com.drosa99.votacao.service;

import com.drosa99.votacao.api.request.PautaRequest;
import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public void cadastrarPauta(PautaRequest pautaRequest) {
        PautaEntity pautaEntity = PautaEntity.builder()
                .descricao(pautaRequest.getDescricao())
                .isAberta(false)
                .build();
        pautaRepository.save(pautaEntity);
    }
}

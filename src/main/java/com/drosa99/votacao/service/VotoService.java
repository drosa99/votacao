package com.drosa99.votacao.service;

import com.drosa99.votacao.api.request.VotoRequest;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.entity.VotoPK;
import com.drosa99.votacao.exception.ExpectedException;
import com.drosa99.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotoService {
    private VotoRepository votoRepository;
    private PautaService pautaService;

    public VotoService(VotoRepository votoRepository, PautaService pautaService) {
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
    }

    public void cadastrarVoto(VotoRequest votoRequest) {
        if (!pautaService.isPautaAberta(votoRequest.getIdPauta())) {
            throw new ExpectedException("error.PautaFechada");
        }
        VotoPK votoPK = transformaRequestEmVotoPk(votoRequest);
        votoRepository.findByVotoPK(votoPK).ifPresent(it -> {
            throw new ExpectedException("error.votoJaCadastrado");
        });
        VotoEntity votoEntity = VotoEntity.builder().votoPK(votoPK).valor(votoRequest.getValor()).build();
        votoRepository.save(votoEntity);
    }

    private VotoPK transformaRequestEmVotoPk(VotoRequest votoRequest) {
        return VotoPK.builder()
                .idAssociado(votoRequest.getIdAssociado())
                .idPauta(votoRequest.getIdPauta())
                .build();
    }
}

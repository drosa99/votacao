package com.drosa99.votacao.service;

import com.drosa99.votacao.api.request.PautaRequest;
import com.drosa99.votacao.api.response.PautaResponse;
import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.exception.ExpectedException;
import com.drosa99.votacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PautaResponse contabilizarResultado(Long id) {
        PautaEntity pautaEntity = pautaRepository.findById(id).orElseThrow(() -> {
            throw new ExpectedException("notFound.pauta");
        });
        List<VotoEntity> votoEntityList = pautaEntity.getVotos();
        long votosSim = votoEntityList.stream().filter(it -> it.getValor().equals(true)).count();
        long votosNao = votoEntityList.stream().filter(it -> it.getValor().equals(false)).count();
        PautaResponse pautaResponse = PautaResponse.builder()
                .descricao(pautaEntity.getDescricao())
                .id(id)
                .votosSim((int) votosSim)
                .votosNao((int) votosNao)
                .resultado(votosSim > votosNao ? "Sim" : "Nao")
                .build();
        return null;
    }

    public Boolean isPautaAberta(Long id) {
        PautaEntity pautaEntity = pautaRepository.findById(id).orElseThrow(() -> {
            throw new ExpectedException("notFound.pauta");
        });
        return pautaEntity.getIsAberta();
    }
}

package com.drosa99.votacao.service;

import com.drosa99.votacao.api.request.PautaRequest;
import com.drosa99.votacao.api.response.PautaResponse;
import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.exception.ExpectedException;
import com.drosa99.votacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PautaService {
    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    //Cadastro de pauta
    public PautaEntity cadastrarPauta(PautaRequest pautaRequest) {
        PautaEntity pautaEntity = PautaEntity.builder()
                .descricao(pautaRequest.getDescricao())
                .comecou(false)
                .build();
        return pautaRepository.save(pautaEntity);
    }

    //Metodo que contabiliza o resultado dos votos da pauta
    public PautaResponse contabilizarResultado(Long id) {
        PautaEntity pautaEntity = getPautaEntity(id);
        List<VotoEntity> votoEntityList = pautaEntity.getVotos();
        long votosSim = votoEntityList.stream().filter(it -> it.getValor().equals(true)).count();
        long votosNao = votoEntityList.stream().filter(it -> it.getValor().equals(false)).count();
        return PautaResponse.builder()
                .descricao(pautaEntity.getDescricao())
                .id(id)
                .votosSim((int) votosSim)
                .votosNao((int) votosNao)
                .resultado(votosSim > votosNao ? "Sim" : votosSim == votosNao ? "Empate" : "Nao")
                .build();
    }

    //Metodo responsavel por iniciar a votacao e definir a data/hora em que sera fechada
    public void iniciarVotacao(Long id, Long tempoAberta) {
        PautaEntity pautaEntity = getPautaEntity(id);
        pautaEntity.setComecou(true);
        Long minutos = 1L;
        if (tempoAberta != null) {
            minutos = tempoAberta;
        }
        LocalDateTime tempoFechamento = LocalDateTime.now().plusMinutes(minutos);
        pautaEntity.setFechamento(tempoFechamento);
        pautaRepository.save(pautaEntity);
    }

    //Metodo auxiliar que verifica se uma pauta esta aberta para votacao, se ela nao estiver,
    // sao lancadas excecoes de ainda nao aberta ou ja encerrada
    public void verificaVotacaoAberta(Long id) {
        PautaEntity pautaEntity = getPautaEntity(id);
        if (!pautaEntity.getComecou()) {
            throw new ExpectedException("error.votacaoAindaNaoIniciada");
        }
        if (LocalDateTime.now().isAfter(pautaEntity.getFechamento())) {
            throw new ExpectedException("error.votacaoFechada");
        }
    }

    //Metodo auxiliar que busca uma Pauta no banco pelo id e a retorna, se ela nao existir, lanca uma excecao de pauta nao encontrada
    private PautaEntity getPautaEntity(Long id) {
        return pautaRepository.findById(id).<ExpectedException>orElseThrow(() -> {
            throw new ExpectedException("notFound.pauta");
        });
    }
}

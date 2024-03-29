package com.drosa99.votacao.service;

import com.drosa99.votacao.api.request.VotoRequest;
import com.drosa99.votacao.api.response.CpfStatus;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.entity.VotoPK;
import com.drosa99.votacao.exception.ExpectedException;
import com.drosa99.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class VotoService {
    private VotoRepository votoRepository;
    private PautaService pautaService;

    public VotoService(VotoRepository votoRepository, PautaService pautaService) {
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
    }

    //Metodo de cadastrar voto, caso ja exista um voto do associado para esta pauta, e lancada excecao
    public void cadastrarVoto(VotoRequest votoRequest) {
        verificaCpf(votoRequest.getIdAssociado());
        pautaService.verificaVotacaoAberta(votoRequest.getIdPauta());
        VotoPK votoPK = transformaRequestEmVotoPk(votoRequest);
        votoRepository.findByVotoPK(votoPK).ifPresent(it -> {
            throw new ExpectedException("error.votoJaCadastrado");
        });
        VotoEntity votoEntity = VotoEntity.builder().votoPK(votoPK).valor(votoRequest.getValor()).build();
        votoRepository.save(votoEntity);
    }

    //Metodo auxiliar que recebe a request de voto e cria a chave primaria com cpf do associado e id da pauta
    private VotoPK transformaRequestEmVotoPk(VotoRequest votoRequest) {
        return VotoPK.builder()
                .idAssociado(votoRequest.getIdAssociado())
                .idPauta(votoRequest.getIdPauta())
                .build();
    }

    //Tarefa bonus 1, este metodo faz a chamada para a api externa e lanca as devidas excecoes caso o cpf seja invalido
    // ou nao esteja apto a votar, caso o cpf esteja apto, nao acontece nada
    private void verificaCpf(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create("https://user-info.herokuapp.com/users/".concat(cpf));
        try {
            CpfStatus cpfStatus = restTemplate.getForObject(url, CpfStatus.class);
            if (cpfStatus.getStatus().equals("UNABLE_TO_VOTE")) {
                throw new ExpectedException("unable.cpf");
            }
        } catch (Exception e) {
            if (e.getMessage().equals("unable.cpf")) {
                throw new ExpectedException("unable.cpf");
            }
            throw new ExpectedException("invalid.cpf");
        }

    }
}

package com.drosa99.votacao;

import com.drosa99.votacao.PautaStub.PautaStub;
import com.drosa99.votacao.api.request.PautaRequest;
import com.drosa99.votacao.api.response.PautaResponse;
import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.repository.PautaRepository;
import com.drosa99.votacao.service.PautaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void cadastroPautaTeste() {
        PautaEntity pautaEntity = PautaStub.create();
        when(pautaRepository.save(any())).thenReturn(pautaEntity);
        PautaEntity pautaSalva = pautaService.cadastrarPauta(PautaRequest.builder().descricao("pauta teste").build());
        assertEquals(pautaSalva.getComecou(), false);
    }

    @Test
    public void contabilizarVotosEmpateTeste() {
        PautaEntity pautaEntity = PautaStub.create();
        when(pautaRepository.findById(any())).thenReturn(Optional.of(pautaEntity));
        pautaEntity.setVotos(Arrays.asList(VotoEntity.builder().valor(true).build(), VotoEntity.builder().valor(false).build()));
        PautaResponse pautaResponse = pautaService.contabilizarResultado(1L);
        assertEquals("Empate", pautaResponse.getResultado());
        assertEquals(1, pautaResponse.getVotosNao());
        assertEquals(1, pautaResponse.getVotosSim());
    }

    @Test
    public void contabilizarVotosSimTeste() {
        PautaEntity pautaEntity = PautaStub.create();
        when(pautaRepository.findById(any())).thenReturn(Optional.of(pautaEntity));
        pautaEntity.setVotos(Arrays.asList(VotoEntity.builder().valor(true).build()));
        PautaResponse pautaResponse = pautaService.contabilizarResultado(1L);
        assertEquals("Sim", pautaResponse.getResultado());
        assertEquals(0, pautaResponse.getVotosNao());
        assertEquals(1, pautaResponse.getVotosSim());
    }

    @Test
    public void contabilizarVotosNaoTeste() {
        PautaEntity pautaEntity = PautaStub.create();
        when(pautaRepository.findById(any())).thenReturn(Optional.of(pautaEntity));
        pautaEntity.setVotos(Arrays.asList(VotoEntity.builder().valor(false).build()));
        PautaResponse pautaResponse = pautaService.contabilizarResultado(1L);
        assertEquals("Nao", pautaResponse.getResultado());
        assertEquals(1, pautaResponse.getVotosNao());
        assertEquals(0, pautaResponse.getVotosSim());
    }

}

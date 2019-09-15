package com.drosa99.votacao.PautaStub;

import com.drosa99.votacao.entity.PautaEntity;

public class PautaStub {
    public static PautaEntity create() {
        return PautaEntity.builder()
                .descricao("pauta teste")
                .comecou(false)
                .id(1L)
                .fechamento(null)
                .votos(null)
                .build();
    }
}

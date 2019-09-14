package com.drosa99.votacao.api.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponse {
    private Long id;
    private String descricao;
    private String resultado;
    private int votosSim;
    private int votosNao;
}

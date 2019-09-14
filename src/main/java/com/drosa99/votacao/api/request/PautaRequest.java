package com.drosa99.votacao.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {

    @ApiModelProperty(value = "Descricao da pauta.")
    @NotNull(message = "descricao.empty")
    private String descricao;
}

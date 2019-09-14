package com.drosa99.votacao.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {

    @ApiModelProperty(value = "Identificador da pauta.")
    @NotNull(message = "pauta.empty")
    private Long idPauta;
    @ApiModelProperty(value = "CPF do associado.")
    @NotNull(message = "associado.empty")
    private String idAssociado;
    @ApiModelProperty(value = "O voto: SIM - true; NAO - false.")
    @NotNull(message = "valor.empty")
    private Boolean valor;
}

package com.drosa99.votacao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoPK implements Serializable {
    private static final long serialVersionUID = -8376435254451245722L;

    @Column(name = "OID_ASSOCIADO")
    private Long idAssociado;

    @Column(name = "OID_PAUTA")
    private Long idPauta;
}

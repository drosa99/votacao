package com.drosa99.votacao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "VOTO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoEntity implements Serializable {
    private static final long serialVersionUID = 7436681895263633949L;

    @EmbeddedId
    protected VotoPK votoPK;

    @Column
    private Boolean valor;

    @JoinColumn(name = "OID_PAUTA", referencedColumnName = "OID_PAUTA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PautaEntity pauta;
}

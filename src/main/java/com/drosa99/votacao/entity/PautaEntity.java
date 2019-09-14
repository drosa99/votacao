package com.drosa99.votacao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PAUTA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaEntity implements Serializable {
    private static final long serialVersionUID = 3647173312725377750L;

    @Id
    @SequenceGenerator(allocationSize = 1, name = "pauta_seq", sequenceName = "pauta_seq")
    @GeneratedValue(generator = "pauta_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "OID_PAUTA")
    private Long id;

    @OneToMany(mappedBy = "pauta")
    private List<VotoEntity> votos;

    @Column
    private String descricao;

    @Column
    private Boolean isAberta;
}

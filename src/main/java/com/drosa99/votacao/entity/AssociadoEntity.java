package com.drosa99.votacao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ASSOCIADO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociadoEntity implements Serializable {
    private static final long serialVersionUID = -6878674716538919141L;

    @Id
    @SequenceGenerator(allocationSize = 1, name = "associado_seq", sequenceName = "associado_seq")
    @GeneratedValue(generator = "associado_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "OID_ASSOCIADO")
    private Long id;
}

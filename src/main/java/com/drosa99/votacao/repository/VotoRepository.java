package com.drosa99.votacao.repository;

import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<VotoEntity, Long> {
    List<VotoEntity> findByPauta(PautaEntity pautaEntity);
}

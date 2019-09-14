package com.drosa99.votacao.repository;

import com.drosa99.votacao.entity.PautaEntity;
import com.drosa99.votacao.entity.VotoEntity;
import com.drosa99.votacao.entity.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<VotoEntity, Long> {
    List<VotoEntity> findByPauta(PautaEntity pautaEntity);

    Optional<VotoEntity> findByVotoPK(VotoPK votoPK);
}

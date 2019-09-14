package com.drosa99.votacao.service;

import com.drosa99.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotoService {
    private VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }
}

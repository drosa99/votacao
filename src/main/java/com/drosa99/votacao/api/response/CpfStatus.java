package com.drosa99.votacao.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CpfStatus {
    private String status;
}

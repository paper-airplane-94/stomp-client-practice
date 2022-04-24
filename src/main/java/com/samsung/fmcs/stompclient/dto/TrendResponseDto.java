package com.samsung.fmcs.stompclient.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrendResponseDto {
    private String requestId;
    private TrendDto trendDto;

    @Builder
    public TrendResponseDto(String requestId, TrendDto trendDto) {
        this.requestId = requestId;
        this.trendDto = trendDto;
    }
}

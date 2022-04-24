package com.samsung.fmcs.stompclient.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrendResponseDto {
    private String requestId;
    private List<TrendDto> trends;

    @Builder
    public TrendResponseDto(String requestId, List<TrendDto> trends) {
        this.requestId = requestId;
        this.trends = trends;
    }
}

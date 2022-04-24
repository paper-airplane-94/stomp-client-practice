package com.samsung.fmcs.stompclient.service;

import com.samsung.fmcs.stompclient.dto.TrendDto;
import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import com.samsung.fmcs.stompclient.dto.TrendResponseDto;
import com.samsung.fmcs.stompclient.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendCoreService {
    private final TrendRepository trendRepository;

    public List<TrendDto> response(TrendRequestDto requestDto) {
        return trendRepository.findAll()
                .stream()
                .map(TrendDto::of)
                .collect(Collectors.toList());
    }
}

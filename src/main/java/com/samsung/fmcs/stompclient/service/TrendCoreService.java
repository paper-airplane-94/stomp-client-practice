package com.samsung.fmcs.stompclient.service;

import com.samsung.fmcs.stompclient.dto.TrendDto;
import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import com.samsung.fmcs.stompclient.dto.TrendResponseDto;
import com.samsung.fmcs.stompclient.entity.Trend;
import com.samsung.fmcs.stompclient.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendCoreService {
    private final TrendRepository trendRepository;

    @Transactional(readOnly = true)
    public void response(TrendRequestDto requestDto, Consumer<TrendResponseDto> consumer) {
        Stream<Trend> stream = trendRepository.streamAll();
        stream.map(trend -> TrendResponseDto.builder()
                        .requestId(requestDto.getRequestId())
                        .trendDto(TrendDto.of(trend))
                        .build())
                .forEach(consumer);
    }


}
